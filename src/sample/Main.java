package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


//TODO PLAN
//-> mozda da uvedem promjenu jezika

//-> mogu napraviti bazu za registrovane korisnike i ako je neko vec registrovan da samo ide na login pa da se nadje u bazi ako vec postoji
//-> na pocetnu treba promijeniti buttone na customer i employee, te u register dodati dva taba za login ili register itd.
//-> login za uposlenika je admin - admin

//-> postojace dvije tabele u bazi (automobili i kupci(fk na automobile))

//-> uposlenik ce imati spisak svih automobila (jedna od kolona je da li je slobodan automobil), moci ce brisati, dodavati te uredjivati automobile
//-> uposlenik ce takodjer imati i spisak kupaca koji vec imaju zaduzeno neko auto, pa ce i tu moci dodavati, brisati i uredjivati
//-> negdje cu pokusati ubaciti izvjestaj, mozda da ispisuje izvjestaj za svaki automobil
//-> uposlenici kada pritisnu na automobil trebaju da vide da li je automobil iznajmljen, kome i mozda kada (ili samo da postoji button gdje ce biti izvjestaj sa ovim informacijama - da se ne poklopi sa edit ili delete)
//-> uposlenici moraju imati mogucnost da rucno unose podatke o kupcu u slucaju da dodje on sam u firmu, to je ustvari samo add kupca

//-> kupac ce imati pristup tabeli automobila i moci ce izabrati neki automobil, nakon biranja izaci ce prozor sa karakteristikama samog automobila (obavezno staviti osnovnu cijenu), te pokusaja slike kako automobil izgleda
//-> kod karakteristika bi trebala imati 4 vrste cijena (osnovnu, do tri dana, 3-15 dana, 15 - dva mjeseca) da bi kupac vidio sta mu odgovara
//-> kod baza mi trebaju buttoni koji ce filtrirati datu bazu po kategoriji, marki, dostupnosti i cijeni i kupac ima pravo da izabere koje ce sortiranje izabrati
//-> postojace search na vrhu baze vjerovatno
//-> u otvorenom prozoru (za karakteristike auta) zeljenog auta ce biti button koji vodi ka rezervaciji tog oznacenog automobila gdje ce biti uneseno koliko ce auto biti iznajmljeno te konacna cijena izracunata
//-> pokusacu dodati izvjestaj ili konacne kupovine ili automobila, takodjer ako uspijem konacan izvjestaj za kupovinu napraviti mogu ga snimiti u pdfu


