package fr.martiben.webgratinos.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import android.content.Context;

/**
 * Utilitaires divers : fichiers, dates...
 * @author martinelli-b
 */
public final class Utilitaires
{

   /**
    * Lecture d'un fichier de Properties
    * @param pContext Contexte android
    * @param pFilePath Chemin du fichier de Properties
    * @return Les Properties
    */
   public static Properties readPropertiesFile(Context pContext, String pFilePath)
   {
      Properties p = new Properties();
      try
      {
         p.load(pContext.getAssets().open(pFilePath));
      }
      catch (IOException exception)
      {
         // TODO Log
      }
      return p;

   }

   /**
    * Lecture d'un fichier de valeurs simples
    * @param pContext Contexte android
    * @param pFilePath Chemin du fichier de valeurs simples
    * @return Les valeurs simples
    */
   public static List<String> readSimpleValuesFile(Context pContext, String pFilePath)
   {
      String[] result = new String[0];
      Object[] propsBrutes = readPropertiesFile(pContext, pFilePath).keySet().toArray();
      result = Arrays.copyOf(propsBrutes, propsBrutes.length, String[].class);
      return Arrays.asList(result);
   }

   /**
    * Recherche d'un property.
    * @param pContext Contexte android
    * @param pFilePath Chemin du fichier de valeurs simples
    * @param pNomProperty le nom de la property recherchée
    * @return la property ou null si introuvable
    */
   public static String readPropertyFromFile(Context pContext, String pFilePath, String pNomProperty)
   {
      return readPropertiesFile(pContext, pFilePath).getProperty(pNomProperty);
   }
}
