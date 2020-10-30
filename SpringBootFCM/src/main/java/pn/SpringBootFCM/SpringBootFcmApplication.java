package pn.SpringBootFCM;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.SendResponse;

@SpringBootApplication
public class SpringBootFcmApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringBootFcmApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootFcmApplication.class, args);
	}

}

//Configuration

@Service
class FCMConfig {
	private static final Logger log = LoggerFactory.getLogger(FCMConfig.class);
	
	@Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;
	
	@PostConstruct
	public void initialize() {
		try {
			FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())).build();
			if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
}


//Controller
@RestController
class PushNotificationController{
	
	@Autowired private PushNotificationService pushNotificationService;
	
	public String sendNotification(@RequestBody PushNotificationRequest request) {
		pushNotificationService.sendNotificationAndDataMessage(request);
		return "success";
	}
}


@Service
class PushNotificationService {
	private static final Logger log = LoggerFactory.getLogger(PushNotificationService.class);
	
	public void sendNotificationAndDataMessage(PushNotificationRequest request)  {
		log.info("inside sendNotification method - PushNotificationService");		
		try {
			Notification notification = new Notification(request.getTitle(),request.getMessage());
			
			Map<String, String> data = new HashMap<>();
			data.put("title", request.getTitle());
			data.put("message", request.getMessage());
			
			AndroidConfig androidConfig = AndroidConfig.builder()
	                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(request.getTopic())
	                .setPriority(AndroidConfig.Priority.HIGH)
	                .setNotification(AndroidNotification.builder().setSound("default")
	                        .setColor("#FFFF00").setTag(request.getTopic()).build()).build();
			List<String> tokens = request.getTokenList();
			
			MulticastMessage multicastMessage = MulticastMessage.builder()
												.setNotification(notification)
												.putAllData(data)
												.setAndroidConfig(androidConfig)
												.addAllTokens(tokens)											
												.build();
			BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(multicastMessage);								
	        log.info(response.getSuccessCount() + " messages were sent successfully");
	        if (response.getFailureCount() > 0) {
	        	List<SendResponse> responses = response.getResponses();
	        	List<String> failedTokens = new ArrayList<>();
	        	for (int i = 0; i < responses.size(); i++) {
	        	    if (!responses.get(i).isSuccessful()) {
	        	      // The order of responses corresponds to the order of the registration tokens.
	        	      failedTokens.add(request.getTokenList().get(i));
	        	    }
	        	 }
	        	log.info("List of tokens that caused failures on sending notification: " + failedTokens);
	        }
		}catch(Exception e) {
			log.error("error reason"+e);
			e.printStackTrace();
		}
		
	}	
	
	
}

class PushNotificationRequest implements Serializable{
	
	private static final long serialVersionUID = 6476386725709508588L;
	private String title;
    private String message;
    private String topic;
    private String token;
    private List<String> tokenList;
    
    public PushNotificationRequest() {
    }

    public PushNotificationRequest(String title, String messageBody, String topicName) {
        this.title = title;
        this.message = messageBody;
        this.topic = topicName;
    }
    
    public PushNotificationRequest(String title, String message, List<String> tokenList) {
		super();
		this.title = title;
		this.message = message;
		this.tokenList = tokenList;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public List<String> getTokenList() {
		return tokenList;
	}

	public void setTokenList(List<String> tokenList) {
		this.tokenList = tokenList;
	}
    
}
