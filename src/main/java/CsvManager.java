import java.io.*;
import java.util.*;

//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;

public class CsvManager extends JDBsql{
    public void exportToFileCsv(String filename)
    {
        int id;
        Integer nr_na_liscie;
        String nazwa;
        double cena;
        Integer ilosc;
        try {
            FileWriter writer = new FileWriter(filename);

            Map<Integer, cNapoj> napojeMap = getPokazCalaBaze();
            if (napojeMap.isEmpty()) {System.out.println("Baza jest pusta"); return;}
            Set<Integer> keysID = napojeMap.keySet();
            for (Integer i : keysID) {
                id = i;
                nr_na_liscie = napojeMap.get(id).getNr_na_liscie();
                nazwa = napojeMap.get(id).getNazwa();
                cena = napojeMap.get(id).getCena();
                ilosc = napojeMap.get(id).getIlosc();

                writer.append(String.valueOf(id));
                writer.append(',');
                writer.append(String.valueOf(nr_na_liscie));
                writer.append(',');
                writer.append(nazwa);
                writer.append(',');
                writer.append(String.valueOf(cena));
                writer.append(',');
                writer.append(String.valueOf(ilosc));
                writer.append('\n');
            }


            writer.flush();
            writer.close();

            System.out.println("Pomyślnie wyeksportowano dane do pliku exporttest1.csv\n\n");
        } catch (IOException e) {
            System.out.println("Błąd exportu danych do pliku exporttest1.csv " + e+"\n");
            e.printStackTrace();
        }

    }


    public void importFromFileCsvToDatabase(String filename)
    {
        List<cNapoj> napoje = new ArrayList<cNapoj>();
        int id;
        Integer nr_na_liscie;
        String nazwa;
        double cena;
        Integer ilosc;
        cNapoj napoj;
        List<cNapoj> drinks = new ArrayList<cNapoj>();
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int i=0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");

                id = Integer.parseInt(parts[0]);
                if(parts[1].equals("null")){
                    nr_na_liscie = null;
                }else nr_na_liscie = Integer.parseInt(parts[1]);
                nazwa = parts[2];
                cena = Double.parseDouble(parts[3]);
                ilosc = Integer.parseInt(parts[4]);

          //      drinks.add(new cNapoj(nr_na_liscie, nazwa, cena, ilosc));
                dodajDoBazy(new cNapoj(nr_na_liscie, nazwa, cena, ilosc), id);

       //         System.out.println(i+". "+id+" "+drinks.get(i).getNr_na_liscie()+" "+drinks.get(i++).toString());
            }


            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Błąd importu danych z pliku importtest1.csv " + e);
           // e.printStackTrace();
        }
        System.out.println("Wiersze z poprawnymi wartościami zostały zaimportowane\n\n");
    }

}
