package br.com.extractor.easyfinance.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Receita;
import br.com.extractor.easyfinance.model.Tipo;
import io.realm.Realm;
import io.realm.RealmResults;

public class Mock {

    private final Realm realm;

    public Mock() {
        realm = Realm.getDefaultInstance();
    }

    public void mockApplication() {
        insertTipoReceita();
        insertTipoDespesa();
        insertDespesa();
        insertReceita();
        realm.close();
    }

    private void insertReceita() {

        RealmResults<Tipo> listTipoReceita = realm.where(Tipo.class).notEqualTo("tipo", 0).findAll();

        List<Receita> receitas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Receita receita = new Receita();
            receita.setValor(getRandomPrice());
            receita.setTipo(getTipo(listTipoReceita));
            receita.setDescricao("Receita " + i);
            receita.setData(getRandomDate());
            receitas.add(receita);
        }

        realm.beginTransaction();
        realm.copyToRealm(receitas);
        realm.commitTransaction();

    }

    private Tipo getTipo(List<Tipo> list) {
        return list.get(Math.abs((new Random().nextInt()) % list.size()));
    }

    private void insertDespesa() {
        RealmResults<Tipo> listTipoDespesa = realm.where(Tipo.class).notEqualTo("tipo", 1)
                .findAll();

        List<Despesa> despesas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Despesa despesa = new Despesa();
            despesa.setValor(getRandomPrice());
            despesa.setTipo(getTipo(listTipoDespesa));
            despesa.setDescricao("Despesa " + i);
            despesa.setData(getRandomDate());
            despesas.add(despesa);
        }

        realm.beginTransaction();
        realm.copyToRealm(despesas);
        realm.commitTransaction();
    }

    private Date getRandomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, 2015);
        int actualMaximum = gc.getActualMaximum(Calendar.DAY_OF_YEAR);
        int dayOfYear = 1 + (int) Math.round(Math.random() * (actualMaximum - 1));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return gc.getTime();
    }

    private double getRandomPrice() {
        return new Random().nextDouble() * 1000;
    }

    private void insertTipoDespesa() {

        List<Tipo> tipos = new ArrayList<>();

        Tipo tipo1 = new Tipo();
        tipo1.setDescricao("Aluguel");
        tipo1.setTipo(0);

        Tipo tipo2 = new Tipo();
        tipo2.setDescricao("Alimentação");
        tipo2.setTipo(0);

        Tipo tipo3 = new Tipo();
        tipo3.setDescricao("Saúde");
        tipo3.setTipo(0);

        Tipo tipo4 = new Tipo();
        tipo4.setDescricao("Carro");
        tipo4.setTipo(0);

        Tipo tipo5 = new Tipo();
        tipo5.setDescricao("Cinema");
        tipo5.setTipo(0);

        Tipo tipo6 = new Tipo();
        tipo6.setDescricao("Farra");
        tipo6.setTipo(0);

        tipos.add(tipo1);
        tipos.add(tipo2);
        tipos.add(tipo3);
        tipos.add(tipo4);
        tipos.add(tipo5);
        tipos.add(tipo6);

        realm.beginTransaction();
        realm.copyToRealm(tipos);
        realm.commitTransaction();

    }

    private void insertTipoReceita() {

        List<Tipo> tipos = new ArrayList<>();

        Tipo tipo1 = new Tipo();
        tipo1.setDescricao("Salário");
        tipo1.setTipo(1);

        Tipo tipo2 = new Tipo();
        tipo2.setDescricao("Bico");
        tipo2.setTipo(1);

        Tipo tipo3 = new Tipo();
        tipo3.setDescricao("Outros");
        tipo3.setTipo(2);

        tipos.add(tipo1);
        tipos.add(tipo2);
        tipos.add(tipo3);

        realm.beginTransaction();
        realm.copyToRealm(tipos);
        realm.commitTransaction();

    }

}
