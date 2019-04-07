
package javaapplication42;

import java.awt.List;
import static java.lang.Math.max;


public class NewClass {
    int[][] plansza = new int[3][3];
    static int znakKomputera = 1;
    static int znakGracza = 2;
    int[] listaWynikow = new int[1000];
    int[][] tablicaWynikow = new int[3][3];
    int a = 0;
    int licznikWykonan = 0;
    int wywolania = 0;
    int liczbaGier = 0;
    public boolean sprawdzCzyWygrana(int[][] plansza)
    {
        
        
            for(int j =0; j<=2; j++)
            {
                if(plansza[j][0]==znakKomputera && plansza[j][1]==znakKomputera && plansza[j][2]==znakKomputera ) {return true;}
                if(plansza[0][j]==znakKomputera && plansza[1][j]==znakKomputera && plansza[2][j]==znakKomputera ) {return true;}
            }
            if(plansza[0][0]==znakKomputera && plansza[1][1]==znakKomputera && plansza[2][2]==znakKomputera ) {return true;}
            if(plansza[0][2]==znakKomputera && plansza[1][1]==znakKomputera && plansza[2][0]==znakKomputera ) {return true;}
        
        return false;
    }
    public boolean sprawdzCzyPrzegrana(int[][] plansza)
    {
        
            for(int j =0; j<=2; j++)
            {
                if(plansza[j][0]==znakGracza && plansza[j][1]==znakGracza && plansza[j][2]==znakGracza ) {return true; }
                if(plansza[0][j]==znakGracza && plansza[1][j]==znakGracza && plansza[2][j]==znakGracza ) {return true;}
            }
            if(plansza[0][0]==znakGracza && plansza[1][1]==znakGracza && plansza[2][2]==znakGracza ) {return true;}
            if(plansza[0][2]==znakGracza && plansza[1][1]==znakGracza && plansza[2][0]==znakGracza ) {return true;}
        
        return false;
    }
    public boolean sprawdzCzyRemis(int[][] plansza)
    {
        boolean czyRemis = true;
        for(int i = 0; i<3; i++)
        {
            for(int j =0; j<3; j++)
            {
                if(plansza[i][j]==0) czyRemis=false;
            }
        }
        return czyRemis;
        
    }
     public int[] sprawdzCzyNastepnyRuchDecydujeOGrze(int[][] plansza)
    {
        int[] wspolrzedne = new int[2];
        for(int i =0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if(plansza[i][j]==0)
                {
                    plansza[i][j] = znakKomputera;
                    if(sprawdzCzyWygrana(plansza)){wspolrzedne[0]=i; wspolrzedne[1]=j; return wspolrzedne;}
                    plansza[i][j] = znakGracza;
                    if(sprawdzCzyPrzegrana(plansza)){wspolrzedne[0]=i; wspolrzedne[1]=j; return wspolrzedne;}
                    plansza[i][j] = 0;
                }
            }
        }
        wspolrzedne[0] = -1;
        wspolrzedne[1] = -1;
        return wspolrzedne;
    }
     
     
     
     //!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     public void ruchy(NewClass plansza, boolean ruchKomputera)
     {
         System.out.println("Wywolanie funkcji ruchy po raz "+ wywolania);
         wywolania++;
         for(int i =0; i<3; i++)
         {
             for(int j=0; j<3; j++)
             {
                 //System.out.println(i+" "+j);
                 //System.out.println("Wykonuje sie "+licznikWykonan);licznikWykonan++;
                 if(plansza.plansza[i][j]==0 && ruchKomputera) {
                     plansza.plansza[i][j]=1;
                     if(!plansza.sprawdzCzyWygrana(plansza.plansza))
                     ruchy(plansza, false);
                     else {a=a+10; licznikWykonan++;}
                     plansza.plansza[i][j]=0;
                 }
                 if(plansza.plansza[i][j]==0 && !ruchKomputera) {plansza.plansza[i][j]=2; 
                 if(!plansza.sprawdzCzyPrzegrana(plansza.plansza))
                 ruchy(plansza, true);
                 else {a=a-10;licznikWykonan++;}
                 plansza.plansza[i][j]=0;
                 }
             }
         }
         
         
         
         //System.out.println("Wykonuje sie "+licznikWykonan);licznikWykonan++;
         
     }
     public int[] optymalnyRuch(NewClass plansza)
     {
         System.out.println("Optymany!!!!!!!!!!!!!!!!!!!!");
         wypelnienie(plansza);
         zerowanie(plansza);
         for(int i = 0; i<3; i++)
         {
             for(int j = 0; j<3; j++)
             {
                 System.out.println("ASDAS");
                 if(plansza.plansza[i][j]==0)
                 {
                 plansza.plansza[i][j] = 1;
                 tablicaWynikow[i][j] = minimax(plansza, false, 0);
                 plansza.plansza[i][j] = 0;
                 }
             }
         }
         for(int i = 0; i<3; i++)
         {
             for(int j = 0; j<3; j++)
             {
                 System.out.println(tablicaWynikow[i][j]+"\n\n");
                 
             }
         }
         
         int max = tablicaWynikow[0][0];
         for(int i = 0; i<3; i++)
         {
             for(int j = 0; j<3; j++)
             {
                 
                if(tablicaWynikow[i][j]>max) max = tablicaWynikow[i][j];  
             }
         }
         for(int i = 0; i<10; i++)
         System.out.println(max);
         int[] wspolrzedne = new int[2];
         for(int i = 0; i<3; i++)
         {
             for(int j = 0; j<3; j++)
             {
                if(tablicaWynikow[i][j] == max)
                {
                    for(int g =0; g<10; g++){System.out.println(i+" "+j);}
                    wspolrzedne[0] = i;
                    wspolrzedne[1] = j;
                    return wspolrzedne;
                }
             }
         }
         return wspolrzedne;
     }
         /*
         for(int i =0; i<3; i++)
         {
             for(int j=0; j<3; j++)
             {
                 a = 0;
                 if(plansza.plansza[i][j]==0)
                 plansza.plansza[i][j]=1;
                 else continue;
                 ruchy(plansza, false);
                 tablicaWynikow[i][j] = tablicaWynikow[i][j] + a;
                 System.out.println(a+" ---------- TO JEST A");
                 plansza.plansza[i][j] = 0;
                 a=0;
             }
         }
         System.out.println(""+licznikWykonan+"!!!!!!!!!!!!!!!@@@@@@@@@@@\n\n\n\n\n\n\n\n\n\n\n");
         int max = tablicaWynikow[0][0];
         for(int i =0; i<3; i++)
         {
             for(int j = 0; j<3; j++)
             {
                 if(tablicaWynikow[i][j]>max) max = tablicaWynikow[i][j];
             }
         }
         int[] wspolrzedne = new int[2];
         for(int i =0; i<3; i++)
         {
             for(int j = 0; j<3; j++)
             {
                 if(tablicaWynikow[i][j]==max) {wspolrzedne[0] = i; wspolrzedne[1] = j; return wspolrzedne;}
             }
         }
         return wspolrzedne;
         
     }
      */
     public void zerowanie(NewClass plansza)
     {
         for(int i =0; i<3; i++)
         {
             for(int j=0; j<3; j++)
             {
                 
                 if(plansza.plansza[i][j]==0) tablicaWynikow[i][j] = 0; 
             }
         }
     }
     public void wypelnienie(NewClass plansz)
     {
         for(int i =0; i<3; i++)
         {
             for(int j=0; j<3; j++)
             {
                 tablicaWynikow[i][j] = -100000; 
             }
         }
     }
         
     
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     
     public int generujRuchyMinMax(NewClass plansza, boolean ruchKomputera)
     {
         for(int i =0; i<3; i++)
         {
             for(int j=0; j<3; j++)
             {
                 if(plansza.plansza[i][j]==0 && ruchKomputera) 
                 {
                     System.out.println("AZZ");
                     int max = - 10;
                     plansza.plansza[i][j] = 1;
                     if(plansza.sprawdzCzyWygrana(plansza.plansza)) 
                     {
                         plansza.plansza[i][j] = 0;
                         return 10;
                     }
                     
                     max = Math.max(max, generujRuchyMinMax(plansza, false));
                     plansza.plansza[i][j] = 0;
                     
                 }
                 if(plansza.plansza[i][j]==0 && !ruchKomputera)
                 {
                     System.out.println("ABB");
                     int min = 10;
                     plansza.plansza[i][j] = 2;
                     if(plansza.sprawdzCzyPrzegrana(plansza.plansza)) 
                     {
                         plansza.plansza[i][j] = 0;
                         return -10;
                     }
                     min = Math.min(min, generujRuchyMinMax(plansza, true));
                     plansza.plansza[i][j] = 0;
                     
                 }
             }
         }
         return 0;
     }
     
     public int minimax(NewClass plansza, boolean komputer, int glebokosc)
     {
         
         if(plansza.sprawdzCzyPrzegrana(plansza.plansza)) {liczbaGier++;return -100+glebokosc;}
         if(plansza.sprawdzCzyWygrana(plansza.plansza)) {liczbaGier++;return 100-glebokosc;}
         if(plansza.sprawdzCzyRemis(plansza.plansza)) {liczbaGier++;return 0;}
         
         if(komputer)
         {
             int max = -1000;
             for(int i=0; i<3; i++)
             {
                 for(int j=0;j<3;j++)
                 {
                     if(plansza.plansza[i][j]==0)
                     {
                         plansza.plansza[i][j]=1;
                         max=Math.max(max,minimax(plansza,false, glebokosc++));
                         plansza.plansza[i][j] = 0;
                         
                     }
                 }
             }
             return max;
         }else
         {
             int min = 1000;
             for(int i=0; i<3; i++)
             {
                 for(int j=0;j<3;j++)
                 {
                     if(plansza.plansza[i][j]==0)
                     {
                         plansza.plansza[i][j]=2;
                         min=Math.min(min,minimax(plansza,true, glebokosc++));
                         plansza.plansza[i][j]=0;
                         
                     }
                 }
             }
             return min;
         }
         
     }
     public boolean czyJestPelna(int[][] plansza)
     {
         for(int i =0; i<3;i++)
         {
             for(int j=0; j<3; j++)
             {
                 if(plansza[i][j]==0) return false;
             }
         }
         return true;
     }
     
}
