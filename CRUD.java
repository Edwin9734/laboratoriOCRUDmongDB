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

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection <Document> collection;



    public void crearUsuario2(){
        mdUsuario usuario = new mdUsuario();
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




}
