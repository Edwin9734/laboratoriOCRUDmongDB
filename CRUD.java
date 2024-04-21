package org.example.Clases;

import org.bson.Document;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class CRUD {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection <Document> collection;
    public void conexion (){
                                                                                                  //cadena de conexion, contiene la información de la insalacion del mongodb
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");    //local host 1
        MongoClientSettings settings = MongoClientSettings.builder()                              //se crean las configuraciones especificas para conexion y manejo de la base de datos
                .applyConnectionString(connectionString)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("Almacenamiento");//miBasedatos
        collection = database.getCollection("Elementos");//usuarios                                       //NOMBRE DE DB
        System.out.println("conexion realizada");
    }





    public void crearUsuario2(){
        mdUsuario usuario = new mdUsuario();
        usuario.setNombre(JOptionPane.showInputDialog("Ingrese el nombre :"));
        usuario.setCorreo(JOptionPane.showInputDialog("Ingrese CORREO:"));
        usuario.setIdTelegram(Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID):")));


        Document document = new Document("Nombre",usuario.getNombre())
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
            u.setIdTelegram(doc.getLong("IdTelegram"));
            u.setNombre(doc.getString("Nombre"));
            u.setCorreo(doc.getString("correo"));//aca se pueden agregar mas columnas
            usuarios.add(u);
        }
        for (mdUsuario u : usuarios){
            System.out.println("usuario:"+u.getNombre()+" Correo:"+ u.getCorreo()+" IdTelegram: "+u.getIdTelegram());
        }
    }

    public void UpDate() {

        long id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID):"));
        String CORRE = JOptionPane.showInputDialog("Ingrese el nuevo Correo:");
        collection.updateOne(Filters.eq("IdTelegram", id),
                Updates.set("correo", CORRE));
        System.out.println("Usuario actualizado");


    }

    public  void eliminarUsuario() {

        collection.deleteOne(Filters.eq("IdTelegram", 3));

        System.out.println("Usuario eliminado");
    }




}
