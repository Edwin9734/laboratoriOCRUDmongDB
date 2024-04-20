package org.example.Clases;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClsUsuariosMongo {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection <Document> collection;

    public void conexion (){
        //cadena de conexion, contiene la información de la insalacion del mongodb
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");

        //se crean las configuraciones especificas para conexion y manejo de la base de datos
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("miBasedatos");
        collection = database.getCollection("usuarios");
        System.out.println("conexion realizada");

    }

    public void crearUsuario(){
        mdUsuario usuario= new mdUsuario();
        usuario.setNombre("MOMO");
        usuario.setCorreo("NANOa@example.com");
        usuario.setIdTelegram(1234567);


        Document  document = new Document("Nombre",usuario.getCorreo())
                .append("correo",usuario.getCorreo())
                .append("IdTelegram",usuario.getIdTelegram());
        // .append("direccion, 5 calle ")

        collection.insertOne(document);
        System.out.println("Usuario Creado");


    }

    public void crearUsuario2(){
        mdUsuario usuario = new mdUsuario();//
        usuario.setCorreo(JOptionPane.showInputDialog("Ingrese el nombre :"));
        usuario.setCorreo(JOptionPane.showInputDialog("Ingrese CORREO:"));
        usuario.setIdTelegram(Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID):")));


        Document document = new Document("Nombre",usuario.getCorreo())
                .append("correo",usuario.getCorreo())
                .append("IdTelegram",usuario.getIdTelegram());
        // .append("direccion, 5 calle ")

        collection.insertOne(document);
        System.out.println("Usuario Creado");


    }




    public void  leerUsuarios(){
        List<mdUsuario> usuarios = new ArrayList<>();

        //leer todos los documentos
        for(Document doc : collection.find() ){
            mdUsuario u = new mdUsuario();
          //  u.setIdTelegram(doc.getLong("IdTelegram"));
            u.setNombre(doc.getString("Nombre"));
            u.setCorreo(doc.getString("correo"));//aca se pueden agregar mas columnas
            usuarios.add(u);


        }
        for (mdUsuario u : usuarios){
            System.out.println("usuario:"+u.getNombre()+" Correo:"+ u.getCorreo());
        }






    }

    private void crud(){
        MongoCollection<Document> collection = mongoClient.getDatabase("miBaseDeDatos").getCollection("miColeccion");

        Document documento = new Document();
        documento.put("nombre", "Juan");
        documento.put("apellido", "Perez");
        documento.put("edad", 30);

        collection.insertOne(documento);



    }


    private  void actualizarUsuario() {
        collection.updateOne(Filters.eq("idTelegram", 88745587),
                Updates.set("correo", "Nuevo@example.com"));
        System.out.println("Usuario actualizado");
    }

    private  void eliminarUsuario() {
        collection.deleteOne(Filters.eq("idTelegram", 9995587));
        System.out.println("Usuario eliminado");
    }

    private  void desconectarMongoDB() {
        // Cerrar la conexión
        mongoClient.close();
        System.out.println("Desconectado de MongoDB");
    }


    private  void conectarMongoDB() {
        // Conexión a MongoDB
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        mongoClient = MongoClients.create(settings);

        // Obtener la base de datos y la colección
        database = mongoClient.getDatabase("miBaseDeDatos");
        collection = database.getCollection("usuarios");
        System.out.println("Conectado a MongoDB");
    }



    //buscar usuario por correo
    //returna un registro
    private  void buscarUnUsuarioCorreo(String correo) {
        Document doc = collection.find(Filters.eq("correo", correo)).first();
        if (doc != null) {
            System.out.println("Usuario encontrado: " + doc.toJson());
        } else {
            System.out.println("Usuario no encontrado");
        }
    }






    //buscar usuario por correo
    //retorna una lista de registros
    private  List<mdUsuario> buscarUsuariosPorCorreo(String correo) {
        List<mdUsuario> usuarios = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find(Filters.eq("correo", correo)).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                mdUsuario u = new mdUsuario();
                u.setIdTelegram(doc.getLong("idTelegram"));
                u.setNombre(doc.getString("nombre"));
                u.setCorreo(doc.getString("correo"));
                usuarios.add(u);
            }
        } finally {
            cursor.close();
        }

        return usuarios;
    }



    //en chatgpt estaba y puede servir para eliminar una cancion  y me gustaria tambien comvinar el proyecto con el formato JSON
    public class Main {
        public static void main(String[] args) {
            // Crear una lista de ejemplo
            List<String> lista = new ArrayList<>();
            lista.add("Elemento 1");
            lista.add("Elemento 2");
            lista.add("Elemento 5");

            // Mostrar la lista antes de eliminar un elemento
            System.out.println("Lista antes de eliminar:");
            for (String elemento : lista) {
                System.out.println(elemento);
            }

            // Eliminar un elemento (por índice)
            lista.remove(1); // Elimina el segundo elemento (índice 1)

            // Mostrar la lista después de eliminar un elemento
            System.out.println("\nLista después de eliminar:");
            for (String elemento : lista) {
                System.out.println(elemento);
            }
        }
    }







}
