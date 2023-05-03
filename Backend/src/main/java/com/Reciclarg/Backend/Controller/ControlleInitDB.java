
package com.Reciclarg.Backend.Controller;

import com.Reciclarg.Backend.model.encuesta.Desecho;
import com.Reciclarg.Backend.model.encuesta.Motivo;
import com.Reciclarg.Backend.model.encuesta.Paradero;
import com.Reciclarg.Backend.model.encuesta.Pregunta;
import com.Reciclarg.Backend.model.encuesta.Recicla;
import com.Reciclarg.Backend.service.encuesta.IDesechoService;
import com.Reciclarg.Backend.service.encuesta.IMotivoService;
import com.Reciclarg.Backend.service.encuesta.IParaderoService;
import com.Reciclarg.Backend.service.encuesta.IPreguntaService;
import com.Reciclarg.Backend.service.encuesta.IReciclaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inicializar")
public class ControlleInitDB {
    
    @Autowired   
    public IDesechoService desechoServ; 
    
    @Autowired   
    public IMotivoService motivoServ; 
    
    @Autowired   
    public IParaderoService paraderoServ; 
    
    @Autowired   
    public IPreguntaService preguntaServ; 
    
     @Autowired   
    public IReciclaService reciclaServ; 
     
     @GetMapping ("/opciones/{clave}")
     public String initOpciones(@PathVariable int clave){
         if (clave == 123456) {
             try {
                 desechos();
                 motivos();
                 paraderos();
                 preguntas();
                 recicla();
                 return "Ingreso Ok";
             } catch (Exception e) {
                 return e.toString();
             }
             
         }else{
             return "Clave invalida";
         }
     }
     
     private void desechos(){
       String [] Desechos = {"Plastico","Vidrio","Metal","Papel","Organico","Carton","Otro"};
       
         for (String Desecho : Desechos) {
             Desecho d = new Desecho();
             d.setTipoDesecho(Desecho);
             desechoServ.SaveDesecho(d);
         }
     }
     
     private void motivos(){
       String [] items = {"Infrestructura","Desconocimiento","Sentido","Interes","Politicas"};
       
         for (String item : items) {
             Motivo o = new Motivo();
             o.setTipoMotivo(item);
             motivoServ.SaveMotivo(o);
         }
     }
     
     private void paraderos(){
       String [] items = {"Vertederos","Cielo Abierto","Incinerado","Reciclado"};
       
         for (String item : items) {
             Paradero o = new Paradero();
             o.setTipoParadero(item);
             paraderoServ.SaveParadero(o);
         }
     }
     
      private void preguntas(){
       String [] items = {"Punto Verde","Trabaja","Vende","No Dice"};
       
         for (String item : items) {
             Pregunta o = new Pregunta();
             o.setPregunta(item);
             preguntaServ.SavePregunta(o);
         }
     }
      
      private void recicla(){
       String [] items = {"Si","No","No Dice"};
       
         for (String item : items) {
             Recicla o = new Recicla();
             o.setTipoRecicla(item);
             reciclaServ.SaveRecicla(o);
         }
     }
}
