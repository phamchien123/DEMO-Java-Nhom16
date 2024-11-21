import java.awt.CardLayout;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Proccess.Vocabulary;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
public final class HOME extends javax.swing.JFrame {
    List<Question> list = new ArrayList<>();
    Question q;
    private static int pos=0;
    private final Vocabulary lsp = new Vocabulary();
    private boolean cothem = true;
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private Timer timer;
    private int seconds;
    private List<Vocabulary> flashcards;
    private Random random;
    private Vocabulary currentVocabulary;
    public HOME() throws SQLException 
    {
        initComponents();
        flashcards = new Vocabulary().getVocabulary();
        random = new Random();
        showNextWord();
        list.add(new Question ("Mr. Lam is a cycle driver in Ho Chi Minh City, who usually has a ________ working day.","Business","Busy","Busily","Busying",2,0));
        list.add(new Question ("You couldn’t give me a helping hand, _____?","Could you","Do you","Couldn’t you","Don’t you",1,0));
        list.add(new Question ("The employees have been working______out to get the job finished ahead of the deadline","narrow","big","flat"," large",3,0));
        list.add(new Question ("If you get a laptop as a reward, what will you use it ____?","by","for","to","with",2,0));
        list.add(new Question ("The bank will insist you produce a driving________or passport as a form of ID.","diploma","certificate","degree"," licence",4,0));
        list.add(new Question ("____ the TRUMPET is the smallest brass instrument, it can play the highest notes of all the brass instruments and often plays in marches or fanfares.","Because","Because","However","Since",2,0));
        list.add(new Question ("His sister is not only a (n) ______________ singer but also a distinguished painter.","famous opera Italian","opera famous Italian","famous Italian opera","Italian famous opera",3,0));
        list.add(new Question ("Mark invented a new game, but it never really _________________ with people.","called for","caught on","cut off ","came across",2,0));
        list.add(new Question ("He fell down when he______towards the pagoda.","run","runs","was running","had ",3,0));
        list.add(new Question ("She will apply for a job _______.","when she is graduating from university","until she graduated from university","after she had graduated from university","as soon as she graduates from university",4,0));
        list.add(new Question ("It ___ without saying that Mrs Ngoc Anh is a very enthusiastic teacher. I love her so much.","goes"," comes","appears","gets",1,0));
        list.add(new Question ("As soon as Ferlin came to party, he immediately set his _______on Melin. Maybe he was captivated by her.","eyes","heart","decision","feeling",1,0));
        list.add(new Question ("English ___________in many countries. ","are spoke","is spoke","are spoken","is  spoken",4,0));
        list.add(new Question ("___________ all the lights and other electric devices, we left the classroom.","Having been turned off","Turning off","To have turned off ","Having turned off",4,0));
        list.add(new Question ("___________ you study for these exams, the better you will do.","The harder","The more","The hardest","The more hard",1,0));
        list.add(new Question ("Ben: “You didn’t go to school yesterday, did you?,Jasmine: “__________. I saw you, but you were talking to someone”","No, I didn’t","Yes, I didn’t","Let me see","I went",4,0));
        list.add(new Question ("John cannot make a _______ to get married to Mary or stay single until he can afford a house and a car. ","decide","decision","decisive","decisively",2,0));
        list.add(new Question ("My father phoned me to say that he would come _______ home late.","a","an","the","no article",4,0));
        list.add(new Question ("A curriculum that ignores ethnic tensions, racial antagonisms, cultural _____ and religious differences is pot relevant.","diversity ","contacts","barriers","levels",1,0));
        list.add(new Question ("It is not easy to ________ our beauty when we get older and older.","develop","maintain","gain","collect",2,0));
       
        String[] colsName = {"Word","Type","Pronounce","Meaning"};
        tableModel.setColumnIdentifiers(colsName); // Set column names for the table model
        tableword.setModel(tableModel); // Connect JTable to the model
        showData(); // Populate the table with data
        setNull(); // Clear text fields
        view();
        viewlist();
    }
    private void showNextWord() {
    if (!flashcards.isEmpty()) 
    { 
        currentVocabulary = flashcards.get(random.nextInt(flashcards.size()));
        lblword.setText(currentVocabulary.getWord());
        lbltype.setText("Type: " + currentVocabulary.getType());
        lblpronounce.setText("Pronounce: " + currentVocabulary.getPronounce());
        lblmeaning.setText(""); lblmeaning.setVisible(false); } }
    public void view()
    {
       q=list.get(pos);
       this.lblcauhoi.setText(q.getQuestion());
       this.rdbA.setText("A. " +q.getAnswerA());
       this.rdbB.setText("B. " +q.getAnswerB());
       this.rdbC.setText("C. " +q.getAnswerC());
       this.rdbD.setText("D. " +q.getAnswerD());
       switch(q.getStatus())
       {
            case 1:
               OnOff(true,false,false,false);
               break;
            case 2:
                OnOff(false,true,false,false);
                break;
            case 3:
                OnOff(false,false,true,false);
                break;
            case 4:
                OnOff(false,false,false,true);
                break;
            default:
                this.buttonGroup1.clearSelection();
                break;
       }
    }
    public void viewlist()
    {
        DefaultListModel model = new DefaultListModel();
        this.Listquestion.setModel(model);
        int i=1;
        for(Question x:list)
        {
            model.addElement("Question " + i);
            i++;
        }
    }
    public int Choice()
    {
        int n=0;
        if(this.rdbA.isSelected())
        {
            n=1;
        }
        else if(this.rdbB.isSelected())
        {
            n=2;
        }
        else if(this.rdbC.isSelected())
        {
            n=3;
        }
        else if(this.rdbD.isSelected())
        {
            n=4;
        }
        return n;
    }
    void OnOff(boolean a,boolean b,boolean c,boolean d)
    {
        this.rdbA.setSelected(a);
        this.rdbB.setSelected(b);
        this.rdbC.setSelected(c);
        this.rdbD.setSelected(d);
    }
    public void showData() throws SQLException 
    {
        List<Vocabulary> lst = lsp.getVocabulary();
        clearData(); // Clear existing data
        try {
            for (Vocabulary item : lst) 
            {
                Object[] rows = {item.getWord(), item.getType(), item.getPronounce(), item.getMeaning()}; // Extract fields
                tableModel.addRow(rows); // Add row to the model
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
    }
    //Xóa toàn bộ các cột trong bảng
    public void clearData() 
    {
        int a = tableModel.getRowCount() - 1;
        for (int i = a; i >= 0; i--) 
        {
            tableModel.removeRow(i);
        }
    }
    private void setNull() {
        txtword.setText(null);
        cbbtype.setSelectedIndex(0);
        txtpronounce.setText(null);
        txtmeaning.setText(null);
        txtword.requestFocus();
        
    }
    private void clearword()
    {
        txtword.setText(null);
        cbbtype.setSelectedIndex(0);
        txtpronounce.setText(null);
        txtmeaning.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        sidebarpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuhome = new javax.swing.JLabel();
        menuexam = new javax.swing.JLabel();
        menustudy = new javax.swing.JLabel();
        contentpanel = new javax.swing.JPanel();
        Homepanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbbtype = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtpronounce = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtmeaning = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnthoat = new javax.swing.JButton();
        btnxoas = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        btnseach = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableword = new javax.swing.JTable();
        Exampanel = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblTimer = new javax.swing.JLabel();
        btnbatdau = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Listquestion = new javax.swing.JList<>();
        lblcauhoi = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        rdbA = new javax.swing.JRadioButton();
        rdbB = new javax.swing.JRadioButton();
        rdbC = new javax.swing.JRadioButton();
        rdbD = new javax.swing.JRadioButton();
        btnnopbai = new javax.swing.JButton();
        StudyByTopicpanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btntutieptheo = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lblpronounce = new javax.swing.JLabel();
        lbltype = new javax.swing.JLabel();
        lblmeaning = new javax.swing.JLabel();
        lblword = new javax.swing.JLabel();
        lblshow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidebarpanel.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("VOCABULARY");

        menuhome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuhome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home (4).png"))); // NOI18N
        menuhome.setText("HOME");
        menuhome.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                menuhomeAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        menuhome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuhomeMouseClicked(evt);
            }
        });

        menuexam.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuexam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exam.png"))); // NOI18N
        menuexam.setText("EXAM");
        menuexam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuexamMouseClicked(evt);
            }
        });

        menustudy.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menustudy.setText("LEARN BY FLASHCARD");
        menustudy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menustudyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sidebarpanelLayout = new javax.swing.GroupLayout(sidebarpanel);
        sidebarpanel.setLayout(sidebarpanelLayout);
        sidebarpanelLayout.setHorizontalGroup(
            sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menustudy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuexam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuhome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sidebarpanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );
        sidebarpanelLayout.setVerticalGroup(
            sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuhome, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuexam, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menustudy, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentpanel.setLayout(new java.awt.CardLayout());

        Homepanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel3.setBackground(new java.awt.Color(0, 102, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("HOME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font(".VnArial", 0, 12)); // NOI18N
        jLabel2.setText("Word:");

        jLabel4.setFont(new java.awt.Font(".VnArial", 0, 12)); // NOI18N
        jLabel4.setText("Type:");

        cbbtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N", "V ", "Adj", "Adv" }));

        jLabel5.setFont(new java.awt.Font(".VnArial", 0, 12)); // NOI18N
        jLabel5.setText("Pronounce:");

        jLabel7.setFont(new java.awt.Font(".VnArial", 0, 12)); // NOI18N
        jLabel7.setText("Meaning:");

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnthoat.setText("Thoát");
        btnthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatActionPerformed(evt);
            }
        });

        btnxoas.setText("Xóa");
        btnxoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(462, 462, 462)
                        .addComponent(btnxoa))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(txtword))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbbtype, 0, 258, Short.MAX_VALUE)
                                .addComponent(txtpronounce)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(txtmeaning, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnthem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnxoas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnthoat)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtpronounce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmeaning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(66, 66, 66)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnsua)
                    .addComponent(btnthoat)
                    .addComponent(btnxoas))
                .addGap(204, 204, 204)
                .addComponent(btnxoa)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel6.setText("Tìm kiếm");

        btnseach.setText("Seach");
        btnseach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnseachActionPerformed(evt);
            }
        });

        btnlammoi.setText("Làm Mới");
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        tableword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableword.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Word", "Type", "Pronounce", "Meaning"
            }
        ));
        tableword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablewordMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableword);

        javax.swing.GroupLayout HomepanelLayout = new javax.swing.GroupLayout(Homepanel);
        Homepanel.setLayout(HomepanelLayout);
        HomepanelLayout.setHorizontalGroup(
            HomepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomepanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HomepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(HomepanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnseach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnlammoi))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HomepanelLayout.setVerticalGroup(
            HomepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomepanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HomepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(HomepanelLayout.createSequentialGroup()
                        .addGroup(HomepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnseach)
                            .addComponent(btnlammoi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))))
        );

        contentpanel.add(Homepanel, "Homepanel");

        jPanel9.setBackground(new java.awt.Color(51, 153, 255));

        jLabel10.setBackground(new java.awt.Color(0, 102, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("EXAM");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Câu hỏi:");

        lblTimer.setBackground(new java.awt.Color(255, 255, 255));
        lblTimer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTimer.setText("10 : 00");

        btnbatdau.setText("Bắt đầu");
        btnbatdau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatdauActionPerformed(evt);
            }
        });

        Listquestion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Listquestion.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Question 1", "Question 2", "Question 3", "Question 4", "Question 5", "Question 6", "Question 7", "Question 8", "Question 9", "Question 10", "Question 11", "Question 12", "Question 13", "Question 14", "Question 15", "Question 16", "Question 17", "Question 18", "Question 19", "Question 20" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Listquestion.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListquestionValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(Listquestion);

        lblcauhoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        rdbA.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbA);
        rdbA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rdbA.setText("A");

        rdbB.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbB);
        rdbB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rdbB.setText("B");

        rdbC.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbC);
        rdbC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rdbC.setText("C");

        rdbD.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdbD);
        rdbD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rdbD.setText("D");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdbB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdbA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdbD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdbC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(rdbA, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addGap(70, 70, 70))
        );

        btnnopbai.setText("Nộp Bài");
        btnnopbai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnopbaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnnopbai, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblcauhoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnbatdau))
                        .addGap(306, 306, 306)
                        .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnbatdau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jLabel8)))
                .addGap(3, 3, 3)
                .addComponent(lblcauhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(40, 40, 40)
                .addComponent(btnnopbai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 949, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ExampanelLayout = new javax.swing.GroupLayout(Exampanel);
        Exampanel.setLayout(ExampanelLayout);
        ExampanelLayout.setHorizontalGroup(
            ExampanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ExampanelLayout.setVerticalGroup(
            ExampanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExampanelLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
        );

        contentpanel.add(Exampanel, "Exampanel");

        StudyByTopicpanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));

        jLabel9.setBackground(new java.awt.Color(0, 102, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("LEARN BY FLASHCARD");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btntutieptheo.setText("Từ tiếp theo");
        btntutieptheo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntutieptheoActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        lblpronounce.setBackground(new java.awt.Color(255, 255, 255));
        lblpronounce.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblpronounce.setText("Pronounce");

        lbltype.setBackground(new java.awt.Color(255, 255, 255));
        lbltype.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbltype.setText("Type");

        lblmeaning.setBackground(new java.awt.Color(255, 255, 255));
        lblmeaning.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblmeaning.setText("Meaning");

        lblword.setBackground(new java.awt.Color(255, 255, 255));
        lblword.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblword.setText("Word");

        lblshow.setText("Showmeaning");
        lblshow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblshowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblpronounce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbltype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblmeaning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(lblshow, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblword, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblpronounce, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltype, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblmeaning, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(lblshow)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntutieptheo)
                .addGap(0, 449, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntutieptheo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(218, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout StudyByTopicpanelLayout = new javax.swing.GroupLayout(StudyByTopicpanel);
        StudyByTopicpanel.setLayout(StudyByTopicpanelLayout);
        StudyByTopicpanelLayout.setHorizontalGroup(
            StudyByTopicpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(StudyByTopicpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        StudyByTopicpanelLayout.setVerticalGroup(
            StudyByTopicpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudyByTopicpanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentpanel.add(StudyByTopicpanel, "StudyByTopicpanel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebarpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(contentpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sidebarpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuhomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuhomeMouseClicked
    // TODO add your handling code here:
    CardLayout cl = (CardLayout) contentpanel.getLayout();
    cl.show(contentpanel, "Homepanel"); // Hiển thị homePanel
    }//GEN-LAST:event_menuhomeMouseClicked

    private void menuexamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuexamMouseClicked
        // TODO add your handling code here:
    CardLayout cl = (CardLayout) contentpanel.getLayout();
    cl.show(contentpanel, "Exampanel"); // Hiển thị examPanel
    }//GEN-LAST:event_menuexamMouseClicked

    private void menuhomeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_menuhomeAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_menuhomeAncestorAdded

    private void btnseachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnseachActionPerformed
        String tim = txttimkiem.getText();
        if (tim.isEmpty()) {
            // Nếu không nhập từ, hiển thị thông báo
            JOptionPane.showMessageDialog(null, "Vui lòng nhập từ cần tìm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // Gọi phương thức seach để tìm kiếm từ
            Vocabulary foundVocab = lsp.seach(tim); // Giả sử lsp là đối tượng của lớp chứa phương thức seach
            if (foundVocab != null) {
                JOptionPane.showMessageDialog(null, "Từ '" + tim + "' đã được tìm thấy!", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
                clearData(); // Xóa dữ liệu cũ trong bảng
                Object[] rows = {foundVocab.getWord(), foundVocab.getType(), foundVocab.getPronounce(), foundVocab.getMeaning()};
                tableModel.addRow(rows); // Thêm hàng mới vào bảng
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy từ '" + tim + "'.", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnseachActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        String wd = txtword.getText();
        String te = (String) cbbtype.getSelectedItem();
        String pe = txtpronounce.getText();
        String me = txtmeaning.getText();

        if(wd.length() == 0 || pe.length() == 0 ||me.length()==0)
        {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập Word ,Pronounce,Meaning", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else if (wd.length() > 20 || pe.length() > 30 || me.length() > 50)
        {
            JOptionPane.showMessageDialog(null, "Word chỉ 20 ký tự, Pronounce tối đa 30 ký tự,Meaning tối đa 50 ký tự ", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else {
            try {
                // Create a new LoaiSP object
                Vocabulary obj = new Vocabulary(wd,te,pe,me); // Assuming LoaiSP has a constructor that takes two strings
                if (cothem) { // If adding a new record
                    lsp.insertData(obj); // Insert the new record
                }
                else
                { // If editing an existing record
                    lsp.editData(obj); // Update the existing record
                }
                //            clearData(); // Clear the table model
                showData(); // Refresh the table with updated data
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            clearword();
    }//GEN-LAST:event_btnthemActionPerformed
    }
    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed

    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // Lấy dữ liệu từ các trường TextField và ComboBox
        // Kiểm tra nếu từ vựng không được nhập
        // Nếu chế độ là sửa (cothem = true)
        if (cothem) {
            Vocabulary obj = new Vocabulary();
            obj.setWord(txtword.getText());
            obj.setType((String) cbbtype.getSelectedItem());
            obj.setPronounce(txtpronounce.getText());
            obj.setMeaning(txtmeaning.getText());
            // Tạo đối tượng Vocabulary với các giá trị từ người dùng nhập

            // Thực hiện cập nhật dữ liệu thông qua phương thức editData
            try {
                boolean success = lsp.editData(obj); // Gọi phương thức editData để sửa dữ liệu
                if (success) {
                    JOptionPane.showMessageDialog(null, "Dữ liệu đã được cập nhật thành công!");
                    showData(); // Làm mới bảng dữ liệu sau khi sửa thành công
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật không thành công, vui lòng thử lại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this,"Do yo want to logout ?","Select",JOptionPane.YES_NO_OPTION);
        if(a==0)
        {
            this.dispose();
        }
    }//GEN-LAST:event_btnthoatActionPerformed

    private void btnxoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoasActionPerformed
        String wd = txtword.getText();
        try {
            if(wd.length()==0)
            JOptionPane.showMessageDialog(null,"Chon 1 loai SP de xoa", "Thongbao",1);
            else
            {
                if(JOptionPane.showConfirmDialog(null, "Ban muon xoa loai " + wd + "nay hay khong?","Thong bao",2)==0)
                {
                    lsp.deleteData(wd);//goi ham xoa du lieu theo ma loai
                    clearData();//Xoa du lieu trong tableModel
                    showData();//Do du lieu vao table Model
                    setNull();//Xoa trang Textfield
                }
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Xóa thất bại","Thong bao",1);
        }
    }//GEN-LAST:event_btnxoasActionPerformed

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        try {
            // TODO add your handling code here:
            showData();
        } catch (SQLException ex) {
            Logger.getLogger(HOME.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void tablewordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablewordMouseClicked
        // TODO add your handling code here:
        try{
            //Lay chi so dong dang chon
            int row =this.tableword.getSelectedRow();
            String wd = this.tableword.getModel().getValueAt(row, 0).toString(); // Word
            String te = this.tableword.getModel().getValueAt(row, 1).toString(); // Type
            String pe = this.tableword.getModel().getValueAt(row, 2).toString(); // Pronounce
            String me = this.tableword.getModel().getValueAt(row, 3).toString();

            this.txtword.setText(wd);
            this.cbbtype.setSelectedItem(te);
            this.txtpronounce.setText(pe);
            this.txtmeaning.setText(me);
        }
        catch (Exception e) {
        }
    }//GEN-LAST:event_tablewordMouseClicked

    private void btnbatdauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatdauActionPerformed
        // TODO add your handling code here:
        lblTimer.setText("10:00");
        seconds = 600;
        JOptionPane.showMessageDialog(this, "Bắt đầu làm bài");
        timer = new Timer(1000, (ActionEvent e) -> {
            if (seconds > 0) 
            {
                seconds--;
                lblTimer.setText(String.format("%02d:%02d", seconds / 60, seconds % 60));
            } 
            else 
            {
                timer.stop();
                lblTimer.setText("00:00");
                JOptionPane.showMessageDialog(null, "Hết giờ!");
                submitQuiz();
            }
        });
        timer.start();
    }//GEN-LAST:event_btnbatdauActionPerformed

    private void ListquestionValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListquestionValueChanged
        // TODO add your handling code here:
        int n=Choice();
        q.setStatus(n);
        list.set(pos,q);  
        pos=this.Listquestion.getSelectedIndex();
        view();
    }//GEN-LAST:event_ListquestionValueChanged
    private void submitQuiz() {
    int correctAnswers = 0;
    for (Question question : list) {
        if (question.getStatus() == question.getCorrectAnswer()) {
            correctAnswers++;
        }
    }
    JOptionPane.showMessageDialog(this, "Số câu trả lời đúng "+ correctAnswers + "/" + list.size() + " ");
    }
    private void btnnopbaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnopbaiActionPerformed
        // TODO add your handling code here:
        int n=Choice();
        q.setStatus(n);
        list.set(pos,q);
       int correctAnswers = 0;
       for (Question question : list) 
       {
        // Kiểm tra xem câu trả lời đã chọn có đúng không
        if (question.getStatus() == question.getCorrectAnswer()) {
            correctAnswers++;
        }
        }
       if(timer!=null)
       {
            lblTimer.setText("00:00");
            timer.stop();
            JOptionPane.showMessageDialog(this, "Đã nộp bài!");
       }
       JOptionPane.showMessageDialog(this, "Số câu trả lời đúng "+ correctAnswers + "/" + list.size() + " ");
    }//GEN-LAST:event_btnnopbaiActionPerformed

    private void menustudyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menustudyMouseClicked
        // TODO add your handling code here:
    CardLayout cl = (CardLayout) contentpanel.getLayout();
    cl.show(contentpanel, "StudyByTopicpanel");
    }//GEN-LAST:event_menustudyMouseClicked

    private void lblshowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblshowActionPerformed
        // TODO add your handling code here:
        lblmeaning.setText(currentVocabulary.getMeaning()); lblmeaning.setVisible(true);
    }//GEN-LAST:event_lblshowActionPerformed

    private void btntutieptheoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntutieptheoActionPerformed
        // TODO add your handling code here:
        showNextWord();
    }//GEN-LAST:event_btntutieptheoActionPerformed
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String args[]) throws SQLException {
        HOME t = new HOME();
        t.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Exampanel;
    private javax.swing.JPanel Homepanel;
    private javax.swing.JList<String> Listquestion;
    private javax.swing.JPanel StudyByTopicpanel;
    private javax.swing.JButton btnbatdau;
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnnopbai;
    private javax.swing.JButton btnseach;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthoat;
    private javax.swing.JButton btntutieptheo;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton btnxoas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbtype;
    private javax.swing.JPanel contentpanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JLabel lblcauhoi;
    private javax.swing.JLabel lblmeaning;
    private javax.swing.JLabel lblpronounce;
    private javax.swing.JButton lblshow;
    private javax.swing.JLabel lbltype;
    private javax.swing.JLabel lblword;
    private javax.swing.JLabel menuexam;
    private javax.swing.JLabel menuhome;
    private javax.swing.JLabel menustudy;
    private javax.swing.JRadioButton rdbA;
    private javax.swing.JRadioButton rdbB;
    private javax.swing.JRadioButton rdbC;
    private javax.swing.JRadioButton rdbD;
    private javax.swing.JPanel sidebarpanel;
    private javax.swing.JTable tableword;
    private javax.swing.JTextField txtmeaning;
    private javax.swing.JTextField txtpronounce;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JTextField txtword;
    // End of variables declaration//GEN-END:variables
}
