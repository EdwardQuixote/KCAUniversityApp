package uk.co.edwardquixote.Chaward.kcaapp.DataBeans;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Edward Quixote on 14/07/2015.
 */
public class BeanExListViewTTClass {

    private Context coxContext;

    public String sGroupName;

    private ArrayList<BeanExListViewTTClass> arylstGroups = new ArrayList<BeanExListViewTTClass>();
    public ArrayList<String> arylstChildren = new ArrayList<String>();

    private String[] saryCars = {"Subaru", "Koenigsegg", "Ferrari", "Lamborghini"};
    private String[] saryDrivers = {"Edward Quixote", "Edward Ngugi", "Edward Ndukui", "Ngugi Ndukui"};
    private String[] saryRanks = {"Rank 1", "Rank 2", "Rank 3", "Rank 4"};

    public BeanExListViewTTClass(String groupName) {

        this.sGroupName = groupName;
    }


    public ArrayList<BeanExListViewTTClass> codeToPrepareAndLoadData() {

        System.out.println("ArrayList<BeanExListViewTTClass> codeToPrepareAndLoadData() has been called");  //  TODO: For Testing ONLY

        BeanExListViewTTClass clsGroup1 = new BeanExListViewTTClass("Monday");
        for (int i = 0; i < saryCars.length; i++) {
            clsGroup1.arylstChildren.add(saryCars[i]);
        }

        BeanExListViewTTClass clsGroup2 = new BeanExListViewTTClass("Tuesday");
        for (int i = 0; i < saryDrivers.length; i++) {
            clsGroup2.arylstChildren.add(saryDrivers[i]);
        }

        BeanExListViewTTClass clsGroup3 = new BeanExListViewTTClass("Wednesday");
        for (int i = 0; i < saryRanks.length; i++) {
            clsGroup3.arylstChildren.add(saryRanks[i]);
        }

        BeanExListViewTTClass clsGroup4 = new BeanExListViewTTClass("Thursday");
        for (int i = 0; i < saryRanks.length; i++) {
            clsGroup3.arylstChildren.add(saryRanks[i]);
        }

        BeanExListViewTTClass clsGroup5 = new BeanExListViewTTClass("Friday");
        for (int i = 0; i < saryRanks.length; i++) {
            clsGroup3.arylstChildren.add(saryRanks[i]);
        }

        BeanExListViewTTClass clsGroup6 = new BeanExListViewTTClass("Saturday");
        for (int i = 0; i < saryRanks.length; i++) {
            clsGroup3.arylstChildren.add(saryRanks[i]);
        }

        BeanExListViewTTClass clsGroup7 = new BeanExListViewTTClass("Sunday");
        for (int i = 0; i < saryRanks.length; i++) {
            clsGroup3.arylstChildren.add(saryRanks[i]);
        }

        arylstGroups.add(clsGroup1);
        arylstGroups.add(clsGroup2);
        arylstGroups.add(clsGroup3);
        arylstGroups.add(clsGroup4);
        arylstGroups.add(clsGroup5);
        arylstGroups.add(clsGroup6);
        arylstGroups.add(clsGroup7);

        return arylstGroups;
    }

}
