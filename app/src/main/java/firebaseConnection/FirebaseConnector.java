package firebaseConnection;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseConnector {
    private static FirebaseConnector firebase_instance = null;
    private static FirebaseAuth mFirebaseAuth = null;
    private FirebaseConnector() {
        mFirebaseAuth = FirebaseAuth.getInstance();
       System.out.println("it worked");
    }

    public static FirebaseConnector getInstance() {
        if(firebase_instance == null) {
            firebase_instance = new FirebaseConnector();
            return firebase_instance;
        }
        return firebase_instance;
    }
    public static void createDogWalker() {

    }
}
