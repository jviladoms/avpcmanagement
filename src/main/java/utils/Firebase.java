package utils;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by ivanmolera on 15/2/17.
 */
public class Firebase {

    private static Firebase instance = null;

    private CountDownLatch loginLatch;
    private FirebaseToken callbackResult;

    private Firebase() {

    }

    public static Firebase getInstance() {
        if(instance == null) {
            instance = new Firebase();
            instance.init();
        }
        return instance;
    }

    private void init() {
        try {
            URL resource =  this.getClass()
                    .getClassLoader()
                    .getResource(Constants.FIREBASE_CREDENTIALS);

            File file = new File(resource.toURI());
            FileInputStream serviceAccount = new FileInputStream(file);

            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl(Constants.FIREBASE_DATABASE)
                    .build();

            if(FirebaseApp.getApps().size() == 0)
                FirebaseApp.initializeApp(firebaseOptions);

            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public FirebaseToken verifyIdToken(String idToken) throws  InterruptedException {
        callbackResult = null;
        loginLatch = new CountDownLatch(1);

        FirebaseAuth.getInstance().verifyIdToken(idToken)
                .addOnSuccessListener(new OnSuccessListener<FirebaseToken>() {
                    @Override
                    public void onSuccess(FirebaseToken decodedToken) {
                        callbackResult = decodedToken;
                        loginLatch.countDown();
                    }
                });

        if (loginLatch.await(5L, TimeUnit.SECONDS)) {
            return callbackResult;
        }
        return callbackResult;
    }
}
