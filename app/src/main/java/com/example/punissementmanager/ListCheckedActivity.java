package com.example.punissementmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.objetsjava.Session;
import com.objetsjava.Stagiaire;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ListCheckedActivity extends AppCompatActivity {

    private LinkedHashMap<String, GroupInfo> team = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();

    private CustomAdapterStagiaire listAdapter;
    private ExpandableListView ExpandableListViewChecked;

    private static final String stagiaire=null;
    String[] tab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        ExpandableListViewChecked = (ExpandableListView) findViewById(R.id.ExpandableListViewChecked);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapterStagiaire(ListCheckedActivity.this, deptList);
        // attach the adapter to the expandable list view
        ExpandableListViewChecked.setAdapter(listAdapter);

        // setOnChildClickListener listener for child row click
        ExpandableListViewChecked.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo = headerInfo.getPlayerName().get(childPosition);
                //display it or do something with it
            /*Toast.makeText(getBaseContext(), " Team And Player :: " + headerInfo.getName()
                    + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();*/
                Intent DetailStagiaire = new Intent(ListCheckedActivity.this, StagiaireActivity.class);
                DetailStagiaire.putExtra(stagiaire, tab[i]);
                startActivity(DetailStagiaire);
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        ExpandableListViewChecked.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);

                return false;
            }
        });
    }

    // load some initial data into out list
    private void loadData() {

        ArrayList<Session> ListSession = ApplicationManager.getInstance().ListSession();

        for (Session S:ListSession){
            for(Integer T:S.getStagiairesIDS()){
                Stagiaire stag = ApplicationManager.getInstance().CreerStagiaire(T);
                addProduct(S.getNom(),stag.getPrenom() + " " + stag.getNom());
            }
        }
    }

    // here we maintain team and player names
    private int addProduct(String teamName, String playerName) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = team.get(teamName);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new GroupInfo();
            headerInfo.setName(teamName);
            team.put(teamName, headerInfo);
            deptList.add(headerInfo);
        }

        // get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getPlayerName();
        // size of the children list
        int listSize = productList.size();
        // add to the counter
        listSize++;

        // create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setName(playerName);
        productList.add(detailInfo);
        headerInfo.setPlayerName(productList);

        // find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }
}

