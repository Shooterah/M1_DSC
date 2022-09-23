package com.tp2.tp2;
import java.util.ArrayList;

public class TempVegetableRep implements VegetableRep {

    private ArrayList<Vegetable> vegeList;

    public TempVegetableRep(){
        vegeList = new ArrayList<Vegetable>();
        this.add(new Vegetable("Carotte", "Orange", 0.20));
        this.add(new Vegetable("Courgette", "Vert", 0.50));
        this.add(new Vegetable("Choux", "Vert", 0.80));
    }

    @Override
    public ArrayList<Vegetable> findAll() {
        return vegeList;
    }

    @Override
    public void add(Vegetable v) {
        vegeList.add(v);
    }
    
}
