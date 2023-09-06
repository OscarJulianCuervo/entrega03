
package com.unal.ejercicio_3;
import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firebase.Firestore;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 *
 * @author JulianCuervo
 */
public class conexion {
    
    public static Firestore  db;
    
    public static void conectarFirebase(){
        
        try {
            FileInputStream serv = new FileInputStream ("ejercicio3_cred.json");
            FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serv))
            .build();
            
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
            System.out.println("Conexion exitosa");
        } catch(IOException e)
        {
            System.out.println("Error: "+ e.getMessage());
        
        }
    }
}
