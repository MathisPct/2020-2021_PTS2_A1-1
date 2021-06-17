/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Database;

import Application.Metier.ActivityStatus;
import Application.Metier.ActivityType;
import Application.Metier.MaterialType;
import Application.Metier.ProjectStatus;

/**
 *
 * @author Eileen
 */
public class Converter {
        /**
     * Permet de permuter une String en ProjectStatus
     * @param s la string à permuter
     * @return l'équivalent de type ProjectStatus
     */
    public static ProjectStatus stringToProjectStatus(String s){
        ProjectStatus res;
        switch(s){
            case "fini" : res = ProjectStatus.ENDED;
                          break;
                          
            case "en attente" : res = ProjectStatus.WAITING;
                          break;
             
            case "en cours" : res = ProjectStatus.WORKING;
                          break;
                          
            case "annule" : res = ProjectStatus.CANCELED;
                          break;
                          
            default : res = null;                
        }
        
        return res;
    }    
    
        /**
     * Permet de permuter un ProjectStatus en String 
     * @param ps le ProjectStatus à permuter
     * @return l'équivalent de type string
     */
    public static String projectStatusToString(ProjectStatus ps){
        String res;
        switch(ps){
            case ENDED : res = "fini";
                          break;
                          
            case WAITING : res = "en attente";
                          break;
             
            case WORKING : res = "en cours";
                          break;
                          
            case CANCELED : res = "annule";
                          break;
                          
            default : res = "";                
        }
        
        return res;
    }
    
    
    
    
    public static ActivityStatus stringToActivityStatus(String s){
        ActivityStatus res;
        switch(s){
            case "prévue" : res = ActivityStatus.PLANNED;
                          break;
                          
            case "terminée" : res = ActivityStatus.ENDED;
                          break;
             
            case "en cours" : res = ActivityStatus.WORKING;
                          break;
                          
            case "annulée" : res = ActivityStatus.CANCELED;
                          break;
                          
            default : res = null;                
        }
        
        return res;
    }
 
    public static String activityStatusToString(ActivityStatus as){
        String res;
        switch(as){
            case PLANNED : res = "prévue";
                          break;
                          
            case ENDED : res = "terminée";
                          break;
             
            case WORKING : res = "en cours";
                          break;
                          
            case CANCELED : res = "annulée";
                          break;
                          
            default : res = "";                
        }
        
        return res;
    }    
    
    
    
    public static ActivityType stringToActivityType (String s){
        ActivityType res;
        switch(s){
            case "déploiement" : res = ActivityType.IMPLEMENTATION;
                          break;
                          
            case "développement" : res = ActivityType.DEVELOPMENT;
                          break;
             
            case "maintenance" : res = ActivityType.MAINTENANCE;
                          break;
                          
            case "migration" : res = ActivityType.MIGRATION;
                          break;
                          
            case "service après vente" : res = ActivityType.AFTER_SALE;
                          break;
                          
            case "solution innovante" : res = ActivityType.INNOVATION;
                          break;                          
                          
            default : res = null;                
        }
        
        return res;
    } 
    
    public static String activityTypeTString (ActivityType at){
        String res;
        switch(at){
            case IMPLEMENTATION : res = "déploiement";
                          break;
                          
            case DEVELOPMENT : res = "développement";
                          break;
             
            case MAINTENANCE : res = "maintenance";
                          break;
                          
            case MIGRATION : res = "migration";
                          break;
                          
            case AFTER_SALE : res = "service après vente";
                          break;
                          
            case INNOVATION: res = "solution innovante" ;
                          break;                          
                          
            default : res = "";                
        }
        
        return res;
    }
    
    public static String materialTypeToString (MaterialType mt){
        String res;
        switch(mt){
            case PERIPHERAL : res = "périphérique";
                break;
                
            case ANALYSIS : res = "analyse";
                break;
                
            case MAINTENANCE : res = "maintenance électronique";
                break;
                
            case SUPPORT : res = "support numérique";
                break; 
                
            case PC : res = "pc";
                break;
                
            case TABLET : res = "tablette";
                break;
                
            case SOFTWARE : res = "logiciel";
                break;
                
            default : res = null;
        }                
        return res;
    }
    
    public static MaterialType StringToMaterialType (String s){
        MaterialType res;
        switch(s){
            case "périphérique" : res = MaterialType.PERIPHERAL;
                break;
                
            case "analyse" : res = MaterialType.ANALYSIS;
                break;
                
            case "maintenance électronique" : res = MaterialType.MAINTENANCE;
                break;
                
            case "support numérique" : res = MaterialType.SUPPORT;
                break; 
                
            case "pc" : res = MaterialType.PC;
                break;
                
            case "tablette" : res = MaterialType.TABLET;
                break;
                
            case "logiciel" : res = MaterialType.SOFTWARE;
                break;
                
            default : res = null;
        }
        return res;
    }
}
