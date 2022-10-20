package org.firstinspires.ftc.teamcode.mina.utils;

import org.firstinspires.ftc.teamcode.mina.RWConfig;
import org.firstinspires.ftc.teamcode.mina.RWRobot;

import java.util.List;

public class Telemetrie {

    public enum TelType {
        INFO, WARN, ERR;
    }

    private TelType telType;
    private String titlu, mesaj;

    public Telemetrie(TelType telType, String titlu, String mesaj){
        this.telType = telType;
        this.titlu = titlu;
        this.mesaj = mesaj;
    }

    public TelType getTelType() {
        return telType;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj){
        this.mesaj = mesaj;
    }

    public void setTelType(TelType telType){
        this.telType = telType;
    }


    public static void addTel(Telemetrie.TelType tt, String titlu, String mesaj){
        int t = -1;
        for (int i = 0; i < RWRobot.telemetrii.size(); i++){
            if(RWRobot.telemetrii.get(i).getTitlu().equals(titlu)){
                t = i;
                break;
            }
        }
        if(t != -1){
            RWRobot.telemetrii.get(t).setTelType(tt);
            RWRobot.telemetrii.get(t).setMesaj(mesaj);
        }else{
            RWRobot.telemetrii.add(new Telemetrie(tt, titlu, mesaj));
        }
        sortTel();
    }
    public static void addTel(String titlu, String mesaj){
        addTel(TelType.INFO, titlu, mesaj);
    }

    private static void sortTel(){
        List<Telemetrie> telemetrieList = RWRobot.telemetrii;
        Telemetrie t;
        for (int i = 0; i < telemetrieList.size()-1; i++) {
            for (int j = i+1; j < telemetrieList.size(); j++) {
                if(telemetrieList.get(i).getTelType() == TelType.ERR &&
                        telemetrieList.get(j).telType != TelType.ERR){
                    t = telemetrieList.get(i);
                    telemetrieList.set(i,telemetrieList.get(j));
                    telemetrieList.set(j, t);
                }else if(telemetrieList.get(i).getTelType() == TelType.WARN &&
                        telemetrieList.get(j).telType != TelType.ERR &&
                        telemetrieList.get(j).telType != TelType.WARN){
                    t = telemetrieList.get(i);
                    telemetrieList.set(i,telemetrieList.get(j));
                    telemetrieList.set(j, t);
                }
            }
        }
        RWRobot.telemetrii = telemetrieList;
    }
}
