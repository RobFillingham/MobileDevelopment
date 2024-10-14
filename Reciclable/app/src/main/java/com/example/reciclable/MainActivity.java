package com.example.reciclable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /*Button btnInsertar;
    Button btnEliminar;
    Button btnModificar;*/
    Button add;
    ContactoAdapter adaptador;
    String[] names = new String[100];
    String[] phoneNumbers = new String[100];
    String[] emails = new String[100];
    String[][] secPersonalities = new String[20][3];


    ArrayList<Contacto> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //btnEliminar = findViewById(R.id.btnEliminar);
        //btnModificar = findViewById(R.id.btnModificar);
        //btnInsertar = findViewById(R.id.btnInsertar);
        add = findViewById(R.id.btnInsertar);

        /*btnEliminar.setOnClickListener(evento);
        btnInsertar.setOnClickListener(evento);
        btnModificar.setOnClickListener(evento);*/
        add.setOnClickListener(evento);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        fillNames();
        fillNumbers();
        fillEmails();
        fillSEC();


            Random random = new Random();
            int n = 20, pos;
            for (int i = 0; i < n; i++) {
                //pos = random.nextInt(99);
                datos.add(new Contacto(secPersonalities[i][0], secPersonalities[i][1], secPersonalities[i][2]));
            }

            adaptador = new ContactoAdapter(datos, new ContactoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Contacto item) {
                    Toast.makeText(getApplicationContext(), "elemento: " + item.getNombre(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, showEach.class);
                    intent.putExtra("name", item.getNombre());
                    intent.putExtra("email", item.getApeMat());
                    intent.putExtra("phone", item.getApePat());
                    startActivityForResult(intent, 10);
                }
            });




            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            //recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

            //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

            recyclerView.setAdapter(adaptador);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            if (data != null && data.getStringExtra("from").equals("delete")) {
                String n2 = data.getStringExtra("name");
                String e = data.getStringExtra("email");
                String p = data.getStringExtra("phone");
                for (int i = 0; i < datos.size(); i++) {
                    if (datos.get(i).getNombre().equals(n2) && datos.get(i).getApeMat().equals(e) && datos.get(i).getApePat().equals(p)) {
                        datos.remove(i);
                        adaptador.notifyItemRemoved(i);
                        break;
                    }
                }
            }
        }
        if( requestCode == 9 && resultCode == RESULT_OK){
            if (requestCode == 9 && resultCode == RESULT_OK) {
                // Use data instead of getIntent() to retrieve the contact information
                if (data != null) {
                    String n2 = data.getStringExtra("name");
                    String e = data.getStringExtra("email");
                    String p = data.getStringExtra("phone");
                    if (n2 != null && e != null && p != null) { // Ensure the data is not null
                        datos.add(new Contacto(n2, e, p));
                        adaptador.notifyItemInserted(datos.size() - 1);
                        Log.d("mine", "added contact");
                    }
                }
            }
        }
    }

    private View.OnClickListener evento = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == add){
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivityForResult(intent, 9);
            }
            /*if (v == btnInsertar) {
                if (datos.size() > 0) {
                    datos.add(1, new Contacto());
                    adaptador.notifyItemInserted(1);
                } else {
                    // If the list is empty, just add to position 0
                    datos.add(new Contacto());
                    adaptador.notifyItemInserted(0);
                }
            } else if (v == btnEliminar) {
                if (datos.size() > 1) {
                    datos.remove(1);
                    adaptador.notifyItemRemoved(1);
                } else {
                    Toast.makeText(getApplicationContext(), "Not enough items to delete", Toast.LENGTH_SHORT).show();
                }
            } else if (v == btnModificar) {
                if (datos.size() > 1) {
                    datos.set(1, new Contacto());
                    adaptador.notifyItemChanged(1);
                } else {
                    Toast.makeText(getApplicationContext(), "Not enough items to modify", Toast.LENGTH_SHORT).show();
                }
            }*/
        }
    };


    private void fillNames(){
        names[0] = "Alice";
        names[1] = "Bob";
        names[2] = "Charlie";
        names[3] = "David";
        names[4] = "Eve";
        names[5] = "Frank";
        names[6] = "Grace";
        names[7] = "Hank";
        names[8] = "Ivy";
        names[9] = "Jack";
        names[10] = "Kathy";
        names[11] = "Leo";
        names[12] = "Mona";
        names[13] = "Nate";
        names[14] = "Olivia";
        names[15] = "Paul";
        names[16] = "Quincy";
        names[17] = "Rachel";
        names[18] = "Sam";
        names[19] = "Tina";
        names[20] = "Uma";
        names[21] = "Victor";
        names[22] = "Wendy";
        names[23] = "Xander";
        names[24] = "Yara";
        names[25] = "Zane";
        names[26] = "Adam";
        names[27] = "Bella";
        names[28] = "Carter";
        names[29] = "Diana";
        names[30] = "Ethan";
        names[31] = "Fiona";
        names[32] = "George";
        names[33] = "Holly";
        names[34] = "Ian";
        names[35] = "Jasmine";
        names[36] = "Kyle";
        names[37] = "Luna";
        names[38] = "Mason";
        names[39] = "Nina";
        names[40] = "Oscar";
        names[41] = "Piper";
        names[42] = "Quinn";
        names[43] = "Riley";
        names[44] = "Sophie";
        names[45] = "Tom";
        names[46] = "Ursula";
        names[47] = "Vince";
        names[48] = "Willow";
        names[49] = "Xena";
        names[50] = "Yusuf";
        names[51] = "Zara";
        names[52] = "Aiden";
        names[53] = "Bianca";
        names[54] = "Colin";
        names[55] = "Daisy";
        names[56] = "Elijah";
        names[57] = "Faith";
        names[58] = "Gavin";
        names[59] = "Hazel";
        names[60] = "Isaac";
        names[61] = "Jade";
        names[62] = "Kevin";
        names[63] = "Lily";
        names[64] = "Mark";
        names[65] = "Nora";
        names[66] = "Owen";
        names[67] = "Paige";
        names[68] = "Quinton";
        names[69] = "Ruby";
        names[70] = "Steven";
        names[71] = "Tara";
        names[72] = "Ulric";
        names[73] = "Violet";
        names[74] = "Wade";
        names[75] = "Ximena";
        names[76] = "Yvette";
        names[77] = "Zach";
        names[78] = "Alicia";
        names[79] = "Bryan";
        names[80] = "Camila";
        names[81] = "Derek";
        names[82] = "Elena";
        names[83] = "Felix";
        names[84] = "Giselle";
        names[85] = "Henry";
        names[86] = "Isabella";
        names[87] = "Jake";
        names[88] = "Kara";
        names[89] = "Logan";
        names[90] = "Mia";
        names[91] = "Nathan";
        names[92] = "Olive";
        names[93] = "Peter";
        names[94] = "Queenie";
        names[95] = "Reed";
        names[96] = "Scarlett";
        names[97] = "Travis";
        names[98] = "Umair";
        names[99] = "Vivian";
    }

    private void fillNumbers(){
        phoneNumbers[0] = "123-456-7890";
        phoneNumbers[1] = "234-567-8901";
        phoneNumbers[2] = "345-678-9012";
        phoneNumbers[3] = "456-789-0123";
        phoneNumbers[4] = "567-890-1234";
        phoneNumbers[5] = "678-901-2345";
        phoneNumbers[6] = "789-012-3456";
        phoneNumbers[7] = "890-123-4567";
        phoneNumbers[8] = "901-234-5678";
        phoneNumbers[9] = "012-345-6789";
        phoneNumbers[10] = "111-222-3333";
        phoneNumbers[11] = "222-333-4444";
        phoneNumbers[12] = "333-444-5555";
        phoneNumbers[13] = "444-555-6666";
        phoneNumbers[14] = "555-666-7777";
        phoneNumbers[15] = "666-777-8888";
        phoneNumbers[16] = "777-888-9999";
        phoneNumbers[17] = "888-999-0000";
        phoneNumbers[18] = "999-000-1111";
        phoneNumbers[19] = "000-111-2222";
        phoneNumbers[20] = "101-202-3030";
        phoneNumbers[21] = "202-303-4040";
        phoneNumbers[22] = "303-404-5050";
        phoneNumbers[23] = "404-505-6060";
        phoneNumbers[24] = "505-606-7070";
        phoneNumbers[25] = "606-707-8080";
        phoneNumbers[26] = "707-808-9090";
        phoneNumbers[27] = "808-909-0000";
        phoneNumbers[28] = "909-000-1111";
        phoneNumbers[29] = "000-123-4567";
        phoneNumbers[30] = "111-234-5678";
        phoneNumbers[31] = "222-345-6789";
        phoneNumbers[32] = "333-456-7890";
        phoneNumbers[33] = "444-567-8901";
        phoneNumbers[34] = "555-678-9012";
        phoneNumbers[35] = "666-789-0123";
        phoneNumbers[36] = "777-890-1234";
        phoneNumbers[37] = "888-901-2345";
        phoneNumbers[38] = "999-012-3456";
        phoneNumbers[39] = "000-234-5678";
        phoneNumbers[40] = "111-345-6789";
        phoneNumbers[41] = "222-456-7890";
        phoneNumbers[42] = "333-567-8901";
        phoneNumbers[43] = "444-678-9012";
        phoneNumbers[44] = "555-789-0123";
        phoneNumbers[45] = "666-890-1234";
        phoneNumbers[46] = "777-901-2345";
        phoneNumbers[47] = "888-012-3456";
        phoneNumbers[48] = "999-234-5678";
        phoneNumbers[49] = "000-345-6789";
        phoneNumbers[50] = "111-456-7890";
        phoneNumbers[51] = "222-567-8901";
        phoneNumbers[52] = "333-678-9012";
        phoneNumbers[53] = "444-789-0123";
        phoneNumbers[54] = "555-890-1234";
        phoneNumbers[55] = "666-901-2345";
        phoneNumbers[56] = "777-012-3456";
        phoneNumbers[57] = "888-234-5678";
        phoneNumbers[58] = "999-345-6789";
        phoneNumbers[59] = "000-456-7890";
        phoneNumbers[60] = "101-123-4567";
        phoneNumbers[61] = "202-234-5678";
        phoneNumbers[62] = "303-345-6789";
        phoneNumbers[63] = "404-456-7890";
        phoneNumbers[64] = "505-567-8901";
        phoneNumbers[65] = "606-678-9012";
        phoneNumbers[66] = "707-789-0123";
        phoneNumbers[67] = "808-890-1234";
        phoneNumbers[68] = "909-901-2345";
        phoneNumbers[69] = "000-012-3456";
        phoneNumbers[70] = "111-234-5678";
        phoneNumbers[71] = "222-345-6789";
        phoneNumbers[72] = "333-456-7890";
        phoneNumbers[73] = "444-567-8901";
        phoneNumbers[74] = "555-678-9012";
        phoneNumbers[75] = "666-789-0123";
        phoneNumbers[76] = "777-890-1234";
        phoneNumbers[77] = "888-901-2345";
        phoneNumbers[78] = "999-012-3456";
        phoneNumbers[79] = "000-234-5678";
        phoneNumbers[80] = "111-345-6789";
        phoneNumbers[81] = "222-456-7890";
        phoneNumbers[82] = "333-567-8901";
        phoneNumbers[83] = "444-678-9012";
        phoneNumbers[84] = "555-789-0123";
        phoneNumbers[85] = "666-890-1234";
        phoneNumbers[86] = "777-901-2345";
        phoneNumbers[87] = "888-012-3456";
        phoneNumbers[88] = "999-234-5678";
        phoneNumbers[89] = "000-345-6789";
        phoneNumbers[90] = "111-456-7890";
        phoneNumbers[91] = "222-567-8901";
        phoneNumbers[92] = "333-678-9012";
        phoneNumbers[93] = "444-789-0123";
        phoneNumbers[94] = "555-890-1234";
        phoneNumbers[95] = "666-901-2345";
        phoneNumbers[96] = "777-012-3456";
        phoneNumbers[97] = "888-234-5678";
        phoneNumbers[98] = "999-345-6789";
        phoneNumbers[99] = "000-456-7890";
    }

    private void fillEmails(){
        emails[0] = "alice@example.com";
        emails[1] = "bob@example.com";
        emails[2] = "charlie@example.com";
        emails[3] = "david@example.com";
        emails[4] = "eve@example.com";
        emails[5] = "frank@example.com";
        emails[6] = "grace@example.com";
        emails[7] = "hank@example.com";
        emails[8] = "ivy@example.com";
        emails[9] = "jack@example.com";
        emails[10] = "kathy@example.com";
        emails[11] = "leo@example.com";
        emails[12] = "mona@example.com";
        emails[13] = "nate@example.com";
        emails[14] = "olivia@example.com";
        emails[15] = "paul@example.com";
        emails[16] = "quincy@example.com";
        emails[17] = "rachel@example.com";
        emails[18] = "sam@example.com";
        emails[19] = "tina@example.com";
        emails[20] = "uma@example.com";
        emails[21] = "victor@example.com";
        emails[22] = "wendy@example.com";
        emails[23] = "xander@example.com";
        emails[24] = "yara@example.com";
        emails[25] = "zane@example.com";
        emails[26] = "adam@example.com";
        emails[27] = "bella@example.com";
        emails[28] = "carter@example.com";
        emails[29] = "diana@example.com";
        emails[30] = "ethan@example.com";
        emails[31] = "fiona@example.com";
        emails[32] = "george@example.com";
        emails[33] = "holly@example.com";
        emails[34] = "ian@example.com";
        emails[35] = "jasmine@example.com";
        emails[36] = "kyle@example.com";
        emails[37] = "luna@example.com";
        emails[38] = "mason@example.com";
        emails[39] = "nina@example.com";
        emails[40] = "oscar@example.com";
        emails[41] = "piper@example.com";
        emails[42] = "quinn@example.com";
        emails[43] = "riley@example.com";
        emails[44] = "sophie@example.com";
        emails[45] = "tom@example.com";
        emails[46] = "ursula@example.com";
        emails[47] = "vince@example.com";
        emails[48] = "willow@example.com";
        emails[49] = "xena@example.com";
        emails[50] = "yusuf@example.com";
        emails[51] = "zara@example.com";
        emails[52] = "aiden@example.com";
        emails[53] = "bianca@example.com";
        emails[54] = "colin@example.com";
        emails[55] = "daisy@example.com";
        emails[56] = "elijah@example.com";
        emails[57] = "faith@example.com";
        emails[58] = "gavin@example.com";
        emails[59] = "hazel@example.com";
        emails[60] = "isaac@example.com";
        emails[61] = "jade@example.com";
        emails[62] = "kevin@example.com";
        emails[63] = "lily@example.com";
        emails[64] = "mark@example.com";
        emails[65] = "nora@example.com";
        emails[66] = "owen@example.com";
        emails[67] = "paige@example.com";
        emails[68] = "quinton@example.com";
        emails[69] = "ruby@example.com";
        emails[70] = "steven@example.com";
        emails[71] = "tara@example.com";
        emails[72] = "ulric@example.com";
        emails[73] = "violet@example.com";
        emails[74] = "wade@example.com";
        emails[75] = "ximena@example.com";
        emails[76] = "yvette@example.com";
        emails[77] = "zach@example.com";
        emails[78] = "alicia@example.com";
        emails[79] = "bryan@example.com";
        emails[80] = "camila@example.com";
        emails[81] = "derek@example.com";
        emails[82] = "elena@example.com";
        emails[83] = "felix@example.com";
        emails[84] = "giselle@example.com";
        emails[85] = "henry@example.com";
        emails[86] = "isabella@example.com";
        emails[87] = "jake@example.com";
        emails[88] = "kara@example.com";
        emails[89] = "logan@example.com";
        emails[90] = "mia@example.com";
        emails[91] = "nathan@example.com";
        emails[92] = "olive@example.com";
        emails[93] = "peter@example.com";
        emails[94] = "queenie@example.com";
        emails[95] = "reed@example.com";
        emails[96] = "scarlett@example.com";
        emails[97] = "travis@example.com";
        emails[98] = "umair@example.com";
        emails[99] = "vivian@example.com";
    }

    public void fillSEC(){
        secPersonalities[0] = new String[]{"Nick Saban", "University of Alabama", "Head Football Coach"};
        secPersonalities[1] = new String[]{"Kirby Smart", "University of Georgia", "Head Football Coach"};
        secPersonalities[2] = new String[]{"John Calipari", "University of Kentucky", "Head Basketball Coach"};
        secPersonalities[3] = new String[]{"Jimbo Fisher", "Texas A&M University", "Head Football Coach"};
        secPersonalities[4] = new String[]{"Eli Drinkwitz", "University of Missouri", "Head Football Coach"};
        secPersonalities[5] = new String[]{"Greg Sankey", "SEC", "Commissioner"};
        secPersonalities[6] = new String[]{"Brian Kelly", "Louisiana State University", "Head Football Coach"};
        secPersonalities[7] = new String[]{"Sam Pittman", "University of Arkansas", "Head Football Coach"};
        secPersonalities[8] = new String[]{"Lane Kiffin", "University of Mississippi", "Head Football Coach"};
        secPersonalities[9] = new String[]{"Josh Heupel", "University of Tennessee", "Head Football Coach"};
        secPersonalities[10] = new String[]{"Mike White", "University of Georgia", "Head Basketball Coach"};
        secPersonalities[11] = new String[]{"Bruce Pearl", "Auburn University", "Head Basketball Coach"};
        secPersonalities[12] = new String[]{"Mark Stoops", "University of Kentucky", "Head Football Coach"};
        secPersonalities[13] = new String[]{"Scott Stricklin", "University of Florida", "Director of Athletics"};
        secPersonalities[14] = new String[]{"Mandy Cohen", "University of South Carolina", "Director of Athletics"};
        secPersonalities[15] = new String[]{"Shane Beamer", "University of South Carolina", "Head Football Coach"};
        secPersonalities[16] = new String[]{"Jerry Stackhouse", "Vanderbilt University", "Head Basketball Coach"};
        secPersonalities[17] = new String[]{"Nate Oats", "University of Alabama", "Head Basketball Coach"};
        secPersonalities[18] = new String[]{"Rick Barnes", "University of Tennessee", "Head Basketball Coach"};
        secPersonalities[19] = new String[]{"Bryan Harsin", "Auburn University", "Head Football Coach"};
    }
}