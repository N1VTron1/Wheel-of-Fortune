package model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static model.Wedge.WedgeType.BANKRUPT;
import static model.Wedge.WedgeType.LOSE_TURN;

public class Wheel {

  ArrayList<Wedge> wedges = null;

  public Wheel() {
    wedges = new ArrayList<>();

    wedges.add(new Wedge(5000));
    wedges.add(new Wedge(600));
    wedges.add(new Wedge(500));
    wedges.add(new Wedge(300));
    wedges.add(new Wedge(500));
    wedges.add(new Wedge(800));
    wedges.add(new Wedge(550));
    wedges.add(new Wedge(400));
    wedges.add(new Wedge(300));
    wedges.add(new Wedge(900));
    wedges.add(new Wedge(500));
    wedges.add(new Wedge(300));
    wedges.add(new Wedge(900));
    wedges.add(new Wedge(BANKRUPT));
    wedges.add(new Wedge(600));
    wedges.add(new Wedge(400));
    wedges.add(new Wedge(300));
    wedges.add(new Wedge(LOSE_TURN));
    wedges.add(new Wedge(800));
    wedges.add(new Wedge(350));
    wedges.add(new Wedge(450));
    wedges.add(new Wedge(700));
    wedges.add(new Wedge(300));
    wedges.add(new Wedge(600));
  }

  public Wedge spin() {
    int randomNum = ThreadLocalRandom.current().nextInt(0, wedges.size());
    return wedges.get(randomNum);
  }

  public static void main(String[] args) {
    Wheel wheel = new Wheel();
    System.out.println("Random wedge: " + wheel.spin());
  }

}