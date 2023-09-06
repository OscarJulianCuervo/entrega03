
package com.unal.ejercicio_3;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JulianCuervo
 */
public class personaProvider {
    
    CollectionReference reference;
    
    static Firestore db;
    
    public static boolean guardarPersona (String coleccion, String documento, 
            Map<String, Object> data){
         
        db = FirestoreClient.getFirestore();
        
        try {
            DocumentReference docRef = db.collection(coleccion).document(documento); 
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Guardado exitoso");
            return true;
        }catch (Exception e){
             System.out.println("Error: "+ e.getMessage());
        }
        return false;
    }
    
        public static boolean actualizarPersona (String coleccion, String documento, 
            Map<String, Object> data){
         
        db = FirestoreClient.getFirestore();
        
        try {
            DocumentReference docRef = db.collection(coleccion).document(documento); 
            ApiFuture<WriteResult> result = docRef.update(data);
            System.out.println("Actualizado exitoso");
            return true;
        }catch (Exception e){
             System.out.println("Error: "+ e.getMessage());
        }
        return false;
    }
        
        public static boolean EliminarPersona (String coleccion, String documento){
         
        db = FirestoreClient.getFirestore();
        
        try {
            DocumentReference docRef = db.collection(coleccion).document(documento); 
            ApiFuture<WriteResult> result = docRef.delete(Precondition.NONE);
            System.out.println("Eliminado exitoso");
            return true;
        }catch (Exception e){
             System.out.println("Error: "+ e.getMessage());
        }
        return false;
    }
        
        public static void cargarTablaPersona(JTable table){
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nombre");
            model.addColumn("Apellido");
            model.addColumn("Tipo de documento");
            model.addColumn("Documento");
            model.addColumn("Celular");
            
            try{
                CollectionReference persona = conexion.db.collection("persona");
                ApiFuture<QuerySnapshot> querySanp = persona.get();
                
                for(DocumentSnapshot document:querySanp.get().getDocuments())
                    model.addRow(new Object[]{
                        document.getString("Nombre"),
                        document.getString("Apellido"),
                        document.getString("Tipo de documento"),
                        document.getDouble("Documento"),
                        document.getDouble("Celular")
                            
                    });
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error: "+e.getMessage());
            }
            
            table.setModel(model);
        }
}
