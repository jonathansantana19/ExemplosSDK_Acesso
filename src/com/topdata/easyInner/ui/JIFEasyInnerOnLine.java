//******************************************************************************
//A Topdata Sistemas de Automação Ltda não se responsabiliza por qualquer
//tipo de dano que este software possa causar, este exemplo deve ser utilizado
//apenas para demonstrar a comunicação com os equipamentos da linha Inner.
//
//Exemplo On-Line
//Desenvolvido em Java.
//                                           Topdata Sistemas de Automação Ltda.
//******************************************************************************

package com.topdata.easyInner.ui;


//Importing the lybraries that we are using
import com.topdata.EasyInner;
import com.topdata.easyInner.dao.DAOUsuariosBio;
import com.topdata.easyInner.entity.Inner;
import com.topdata.easyInner.entity.UsuarioSemDigital;
import com.topdata.easyInner.enumeradores.Enumeradores;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.topdata.easyInner.dao.DAOUsuarios;
import com.topdata.easyInner.entity.Usuarios;
import com.topdata.easyInner.dao.DAOHorarios;
import static com.topdata.easyInner.dao.DAOHorarios.consultarHorarios;
import com.topdata.easyInner.entity.Horarios;
import static com.topdata.easyInner.utils.EasyInnerUtils.remZeroEsquerda;
import java.io.IOException;
import java.sql.SQLException;


//
public class JIFEasyInnerOnLine extends javax.swing.JInternalFrame {
    private static final long serialVersionUID = 1L;


    //Declaração de variáveis
    private boolean Parar                 = false;
    private Inner InnerAtual              = null;
    private final EasyInner easyInner;
    private int Ret;

    //Catraca
    private boolean LiberaEntrada          = false;
    private boolean LiberaSaida            = false;
    private boolean LiberaEntradaInvertida = false;
    private boolean LiberaSaidaInvertida   = false;

    //Teclado
    public static String ultCartao;
    public static int intTentativas;
    
    /*Esse temporizador ser para que o teclado nao fique bloqueado, 
    caso o usuário nao digite nenhum caractere, caso o tempo for excedido, 
    a possibilidade de digitar senha sera bloqueada e seu acesso será negado*/
    private long TempInitDigHabilitada;

    
    
    //******************************************************
    //MAIS DE UM INNER
    //Array de Inners utilizados na maquina de estados..
    public static Inner[] typInnersCadastrados = new Inner[255];

    public static int lngInnerAtual;

    //Quantidade total de Inners na maquina de estados..
    public static int TotalInners; 

    //tentativas para coleta de bilhetes
    int TentativasColeta;   
    

    private DAOUsuariosBio UsersBio;
    static List<String> Users = null;

    

    private String NumCartao = new String();
    private String Bilhetee;

    private HashMap<String, Object> DadosSmartCard = new HashMap<>();

    private DefaultTableModel dtm;

    @SuppressWarnings("unchecked")
    
    public JIFEasyInnerOnLine() {

        //Inicialização dos componentes
        initComponents();
        InnerAtual = new Inner();
        easyInner = new EasyInner();

        //Seta campos
        jSpnNumInner.setValue(1);
        jSpnQtdDigitos.setValue(14);
        jSpnPorta.setValue(3570);

        jChkBiometrico.setSelected(false);
        jChkDoisLeitores.setSelected(false);

        //**************************************************
        //Carregamento das combos e campos
        //**************************************************
        //Padrão cartão
        jCboPadraoCartao.addItem("TopData");
        jCboPadraoCartao.addItem("Livre");
        jCboPadraoCartao.setSelectedIndex(1);

        jCboPadraoCartao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (jCboPadraoCartao.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Este tipo de cartão é para uso exclusivo de cartões fabricado pela Topdata !");
                }
            }
        });

        //Combo Tipo Conexão
        jCboTipoConexao.removeAllItems();
        jCboTipoConexao.addItem("Serial");
        jCboTipoConexao.addItem("TCP/IP");
        jCboTipoConexao.addItem("TCP/IP porta fixa");
        jCboTipoConexao.setSelectedIndex(2);   //Default

        //Combo Tipo Leitor
        jCboTipoLeitor.removeAllItems();
        jCboTipoLeitor.addItem("Código Barras");
        jCboTipoLeitor.addItem("Magnético");
        jCboTipoLeitor.addItem("Prox. Abatrack/Smart Card");
        jCboTipoLeitor.addItem("Prox. Wiegand/Smart Card");
        jCboTipoLeitor.addItem("Prox. Smart Card Serial");
        jCboTipoLeitor.addItem("Codigo barras serial");
        jCboTipoLeitor.addItem("Wiegand FC sem zero");
        jCboTipoLeitor.setSelectedIndex(2);  //Default

        //Combo Equipamento
        jCboEquipamento.removeAllItems();
        jCboEquipamento.addItem("Não utilizado(Coletor)");
        jCboEquipamento.addItem("Catraca Entrada/Saída");
        jCboEquipamento.addItem("Catraca Entrada");
        jCboEquipamento.addItem("Catraca Saída");
        jCboEquipamento.addItem("Catraca Saída Liberada");
        jCboEquipamento.addItem("Catraca Entrada Liberada");
        jCboEquipamento.addItem("Catraca Liberada 2 Sentidos");
        jCboEquipamento.addItem("Catraca Liberada 2 Sentidos(Sentido Giro)");
        jCboEquipamento.addItem("Catraca com Urna");
        jCboEquipamento.setSelectedIndex(1);  //Default

        //Desabilita botões
        jBtnEntrada.setEnabled(false);
        jBtnParar.setEnabled(false);
        jBtnSaida.setEnabled(false);
        jBtnIniciar.setEnabled(false);
        jBtnLimpar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jCboTipoConexao = new javax.swing.JComboBox();
        lblInners = new javax.swing.JLabel();
        lblNumDig = new javax.swing.JLabel();
        jCboTipoLeitor = new javax.swing.JComboBox();
        jChkDoisLeitores = new javax.swing.JCheckBox();
        lblPorta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblCatraca = new javax.swing.JLabel();
        lblTipoEquipamento = new javax.swing.JLabel();
        jCboEquipamento = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jChkBiometrico = new javax.swing.JCheckBox();
        jChkListaBio = new javax.swing.JCheckBox();
        jChkVerificacao = new javax.swing.JCheckBox();
        jChkIdentificacao = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jChkHorarios = new javax.swing.JCheckBox();
        jChkLista = new javax.swing.JCheckBox();
        jChkTeclado = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jChkCartaoMaster = new javax.swing.JCheckBox();
        jTxtCartaoMaster = new javax.swing.JTextField();
        jSpnQtdDigitos = new javax.swing.JSpinner();
        jSpnNumInner = new javax.swing.JSpinner();
        jSpnPorta = new javax.swing.JSpinner();
        jCboPadraoCartao = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jOptEsquerda = new javax.swing.JRadioButton();
        jOptDireita = new javax.swing.JRadioButton();
        imgCatraca = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDadosRec = new javax.swing.JLabel();
        lblStatuss = new javax.swing.JLabel();
        jLblDados = new javax.swing.JLabel();
        jLblStatus = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxaBilhetes = new javax.swing.JTextArea();
        jBtnParar = new javax.swing.JButton();
        jBtnIniciar = new javax.swing.JButton();
        jBtnEntrada = new javax.swing.JButton();
        jBtnSaida = new javax.swing.JButton();
        jBtnLimpar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTxaVersao = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblInners = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jBtnAdcionarInner = new javax.swing.JButton();
        jBtnRemoverInner = new javax.swing.JButton();

        setIconifiable(true);
        setMaximizable(true);
        setTitle("Exemplo OnLine EasyInner.dll");
        setMaximumSize(new java.awt.Dimension(800, 700));
        setMinimumSize(new java.awt.Dimension(800, 700));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 700));
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurações :"));
        jPanel1.setMinimumSize(new java.awt.Dimension(473, 153));

        jCboTipoConexao.setName("jCboTipoConexao"); // NOI18N

        lblInners.setText("Número Inner:");
        lblInners.setName("lblInners"); // NOI18N

        lblNumDig.setText("Qtd de Dígitos:");
        lblNumDig.setName("lblNumDig"); // NOI18N

        jCboTipoLeitor.setName("jCboTipoLeitor"); // NOI18N
        jCboTipoLeitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCboTipoLeitorMouseClicked(evt);
            }
        });

        jChkDoisLeitores.setSelected(true);
        jChkDoisLeitores.setText("2 Leitores?");
        jChkDoisLeitores.setName("jChkDoisLeitores"); // NOI18N

        lblPorta.setText("Porta:");
        lblPorta.setName("lblPorta"); // NOI18N

        jLabel1.setText("Tipo Conexão:");

        jLabel2.setText("Tipo Leitor:");

        jLabel3.setText("Parâmetros:");

        lblCatraca.setText("Lado catraca entrada");
        lblCatraca.setEnabled(false);
        lblCatraca.setName(""); // NOI18N

        lblTipoEquipamento.setText("Tipo Equipamento:");

        jCboEquipamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboEquipamentocboequipamento_itemstatechanged(evt);
            }
        });
        jCboEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboEquipamentoActionPerformed(evt);
            }
        });

        jPanel4.setLayout(new java.awt.GridLayout(4, 0, 0, 3));

        jChkBiometrico.setText("Biometria");
        jChkBiometrico.setName("jChkBiometrico"); // NOI18N
        jChkBiometrico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jChkBiometricoStateChanged(evt);
            }
        });
        jChkBiometrico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkBiometricoActionPerformed(evt);
            }
        });
        jPanel4.add(jChkBiometrico);

        jChkListaBio.setText("Lista sem Bio OffLine");
        jChkListaBio.setEnabled(false);
        jPanel4.add(jChkListaBio);

        jChkVerificacao.setText("Verificação");
        jChkVerificacao.setEnabled(false);
        jChkVerificacao.setName("jChkVerificacao"); // NOI18N
        jChkVerificacao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jChkVerificacaoStateChanged(evt);
            }
        });
        jChkVerificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkVerificacaoActionPerformed(evt);
            }
        });
        jPanel4.add(jChkVerificacao);

        jChkIdentificacao.setText("Identificação");
        jChkIdentificacao.setEnabled(false);
        jChkIdentificacao.setName("jChkIdentificacao"); // NOI18N
        jChkIdentificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkIdentificacaoActionPerformed(evt);
            }
        });
        jPanel4.add(jChkIdentificacao);

        jPanel5.setLayout(new java.awt.GridLayout(3, 0, 0, 4));

        jChkHorarios.setText("Horários");
        jPanel5.add(jChkHorarios);

        jChkLista.setText("Lista OffLine");
        jChkLista.setName("chkIdentificacao"); // NOI18N
        jPanel5.add(jChkLista);

        jChkTeclado.setSelected(true);
        jChkTeclado.setText("Teclado");
        jChkTeclado.setName("jChkTeclado"); // NOI18N
        jPanel5.add(jChkTeclado);

        jChkCartaoMaster.setText("Cartão Master");
        jChkCartaoMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkCartaoMasterActionPerformed(evt);
            }
        });

        jTxtCartaoMaster.setText("0");
        jTxtCartaoMaster.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addComponent(jChkCartaoMaster))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTxtCartaoMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jChkCartaoMaster)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTxtCartaoMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel4.setText("Padrão Cartão:");

        buttonGroup2.add(jOptEsquerda);
        jOptEsquerda.setText("Esquerda");
        jOptEsquerda.setEnabled(false);
        jOptEsquerda.setName("optCatraca"); // NOI18N
        jOptEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOptEsquerdaActionPerformed(evt);
            }
        });

        buttonGroup2.add(jOptDireita);
        jOptDireita.setSelected(true);
        jOptDireita.setText("Direita");
        jOptDireita.setEnabled(false);
        jOptDireita.setName("optCatraca"); // NOI18N
        jOptDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOptDireitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jOptEsquerda)
                    .addComponent(jOptDireita))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imgCatraca, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jOptEsquerda)
                        .addGap(18, 18, 18)
                        .addComponent(jOptDireita))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imgCatraca, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblInners)
                        .addGap(11, 11, 11)
                        .addComponent(lblPorta)
                        .addGap(38, 38, 38)
                        .addComponent(lblNumDig)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSpnNumInner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jSpnPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jSpnQtdDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCboTipoConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jCboTipoLeitor, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCboPadraoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jCboEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(87, 87, 87)
                        .addComponent(lblTipoEquipamento)
                        .addGap(266, 266, 266)
                        .addComponent(jChkDoisLeitores)))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCatraca)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInners)
                    .addComponent(lblPorta)
                    .addComponent(lblNumDig)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpnNumInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpnPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpnQtdDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCboTipoConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCboTipoLeitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lblTipoEquipamento)
                    .addComponent(jChkDoisLeitores))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCboPadraoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCboEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCatraca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDadosRec.setText("Dados recebidos:");
        lblDadosRec.setName("lblDadosRec"); // NOI18N

        lblStatuss.setText("Status:");
        lblStatuss.setName("lblStatuss"); // NOI18N

        jLblDados.setName("jLblDados"); // NOI18N

        jLblStatus.setMinimumSize(new java.awt.Dimension(200, 0));
        jLblStatus.setName("jLblStatus"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblStatuss)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblDadosRec)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLblDados, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDadosRec, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStatuss, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel8.setText("Bilhetes coletados");
        jLabel8.setName("lblBilhetesOffColetados"); // NOI18N

        jTxaBilhetes.setColumns(20);
        jTxaBilhetes.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTxaBilhetes.setRows(5);
        jScrollPane1.setViewportView(jTxaBilhetes);

        jBtnParar.setText("Parar");
        jBtnParar.setName("jBtnParar"); // NOI18N
        jBtnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPararActionPerformed(evt);
            }
        });

        jBtnIniciar.setText("Iniciar");
        jBtnIniciar.setName("jBtnIniciar"); // NOI18N
        jBtnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIniciarActionPerformed(evt);
            }
        });

        jBtnEntrada.setLabel("Entrada");
        jBtnEntrada.setSelected(true);
        jBtnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEntradaActionPerformed(evt);
            }
        });

        jBtnSaida.setLabel("Saida");
        jBtnSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaidaActionPerformed(evt);
            }
        });

        jBtnLimpar.setText("Limpar");
        jBtnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimparActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setViewportView(jTxaVersao);

        jPanel3.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTblInners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Inner", "Qtd Dígitos", "Teclado", "Lista Off", "ListaBio", "TipoLeitor", "Identificacao", "Verificacao", "DoisLeitores", "Catraca", "Biometrico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTblInners.setMaximumSize(new java.awt.Dimension(1000, 900));
        jTblInners.setMinimumSize(new java.awt.Dimension(1000, 900));
        jTblInners.setPreferredSize(new java.awt.Dimension(1000, 900));
        jScrollPane2.setViewportView(jTblInners);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jBtnAdcionarInner.setText("Incluir na Lista");
        jBtnAdcionarInner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAdcionarInnerActionPerformed(evt);
            }
        });
        jPanel9.add(jBtnAdcionarInner);

        jBtnRemoverInner.setText("Remover da Lista");
        jBtnRemoverInner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRemoverInnerActionPerformed(evt);
            }
        });
        jPanel9.add(jBtnRemoverInner);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jBtnSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                .addComponent(jBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnParar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(621, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEntrada)
                    .addComponent(jBtnIniciar)
                    .addComponent(jBtnParar)
                    .addComponent(jBtnLimpar)
                    .addComponent(jBtnSaida))
                .addContainerGap())
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8)
                    .addGap(4, 4, 4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");
        jBtnEntrada.getAccessibleContext().setAccessibleName("cmdEntrada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 88, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void jBtnRemoverInnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRemoverInnerActionPerformed
        new Thread(new Runnable() {

            @Override
            public void run() {
                if (jTblInners.getSelectedRowCount() > 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTblInners.getModel();
                    dtm.removeRow(jTblInners.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Selecione um Inner na lista !");
                }
            }

        }).start();
    }//GEN-LAST:event_jBtnRemoverInnerActionPerformed

    private void jBtnAdcionarInnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAdcionarInnerActionPerformed
        new Thread(new Runnable() {

            @Override
            public void run() {
                DefaultTableModel listaInners = (DefaultTableModel) jTblInners.getModel();

                for (int i = 0; i < listaInners.getRowCount(); i++) {
                    if (jSpnNumInner.getValue().toString().equals(listaInners.getValueAt(i, 0).toString())) {
                        JOptionPane.showMessageDialog(rootPane, "Numero do Inner já existente !");
                        return;
                    }
                }

                listaInners.addRow(new Object[]{
                    jSpnNumInner.getValue(), jSpnQtdDigitos.getValue(),
                    jChkTeclado.isSelected(), jChkLista.isSelected(), jChkListaBio.isSelected(), jCboTipoLeitor.getSelectedIndex(),
                    jChkIdentificacao.isSelected(), jChkVerificacao.isSelected(), jChkDoisLeitores.isSelected(),
                    jCboEquipamento.getSelectedIndex() != Enumeradores.Acionamento_Coletor, jChkBiometrico.isSelected()});

                jBtnIniciar.setEnabled(jTblInners.getRowCount() > 0);
            }
        }).start();
    }//GEN-LAST:event_jBtnAdcionarInnerActionPerformed

    private void jBtnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimparActionPerformed
        //Exclui bilhetes coletados
        jTxaBilhetes.setText("");
    }//GEN-LAST:event_jBtnLimparActionPerformed

    private void jBtnSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaidaActionPerformed

        if (typInnersCadastrados[lngInnerAtual].Catraca) {
            //Botão que efetua a Saída da Catraca
            habilitaLadoCatraca("Saida");
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;

            //Desabilita botões
            jBtnEntrada.setEnabled(false);
            jBtnSaida.setEnabled(false);
        } else {
            //Desabilita botões
            jBtnEntrada.setEnabled(false);
            jBtnSaida.setEnabled(false);
            Inner.rele = false;
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ACIONAR_RELE;
            //            easyInner.AcionarBipCurto(typInnersCadastrados[lngInnerAtual].Numero);
            //            easyInner.AcionarRele2(typInnersCadastrados[lngInnerAtual].Numero);
            //habilita botões
            jBtnEntrada.setEnabled(true);
            jBtnSaida.setEnabled(true);
        }
    }//GEN-LAST:event_jBtnSaidaActionPerformed

    private void jBtnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEntradaActionPerformed

        if (typInnersCadastrados[lngInnerAtual].Catraca) {
            //Botão que efetua a Entrada da Catraca
            habilitaLadoCatraca("Entrada");
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;

            //Desabilita botões
            jBtnEntrada.setEnabled(false);
            jBtnSaida.setEnabled(false);
        } else {
            //Desabilita botões
            jBtnEntrada.setEnabled(false);
            jBtnSaida.setEnabled(false);
            Inner.rele = true;
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ACIONAR_RELE;
            //            easyInner.AcionarBipCurto(typInnersCadastrados[lngInnerAtual].Numero);
            //            easyInner.AcionarRele1(typInnersCadastrados[lngInnerAtual].Numero);
            //habilita botões
            jBtnEntrada.setEnabled(true);
            jBtnSaida.setEnabled(true);
        }
    }//GEN-LAST:event_jBtnEntradaActionPerformed

    private void jBtnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIniciarActionPerformed
        //Botão que inicia a configuração e conexão
        try {
            UsersBio = new DAOUsuariosBio();
            //Users = 
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        iniciarMaquinaEstados();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JIFEasyInnerOnLine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jBtnIniciarActionPerformed

    private void jBtnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPararActionPerformed
        //Botão que cancela a conexão

        //Desabilita botões
        jBtnEntrada.setEnabled(false);
        jBtnParar.setEnabled(false);
        jBtnSaida.setEnabled(false);
        Parar = true;

        //Mensagem Status
        jLblStatus.setText("Interrupção solicitada.");

        //Habilita botão
        jBtnIniciar.setEnabled(true);

        //Fecha porta comunicação
        easyInner.FecharPortaComunicacao();
    }//GEN-LAST:event_jBtnPararActionPerformed

    private void jOptDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOptDireitaActionPerformed
        imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/direita-java.JPG"))); // NOI18N
    }//GEN-LAST:event_jOptDireitaActionPerformed

    private void jOptEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOptEsquerdaActionPerformed
        imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/esquerda-java.JPG"))); // NOI18N
    }//GEN-LAST:event_jOptEsquerdaActionPerformed

    private void jChkCartaoMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkCartaoMasterActionPerformed
        jTxtCartaoMaster.setEnabled(jChkCartaoMaster.isSelected());
    }//GEN-LAST:event_jChkCartaoMasterActionPerformed

    private void jChkIdentificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkIdentificacaoActionPerformed
        if (!jChkIdentificacao.isSelected() && !jChkVerificacao.isSelected()) {
            jChkBiometrico.setSelected(false);
        } else {
            jChkBiometrico.setSelected(true);
        }
    }//GEN-LAST:event_jChkIdentificacaoActionPerformed

    private void jChkVerificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkVerificacaoActionPerformed

    }//GEN-LAST:event_jChkVerificacaoActionPerformed

    private void jChkVerificacaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jChkVerificacaoStateChanged
        //Habilita/Desabilita campos
        if (!jChkVerificacao.isSelected()) {
            jChkListaBio.setSelected(false);
        }
    }//GEN-LAST:event_jChkVerificacaoStateChanged

    private void jChkBiometricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkBiometricoActionPerformed
        if (jChkBiometrico.isSelected()) {
            jChkVerificacao.setEnabled(true);
            jChkIdentificacao.setEnabled(true);
            jChkListaBio.setEnabled(true);
            jChkIdentificacao.setSelected(true);
        } else {
            jChkVerificacao.setEnabled(false);
            jChkIdentificacao.setEnabled(false);
            jChkListaBio.setEnabled(false);
            jChkIdentificacao.setSelected(false);
            jChkVerificacao.setSelected(false);
            jChkListaBio.setSelected(false);
        }
    }//GEN-LAST:event_jChkBiometricoActionPerformed

    private void jChkBiometricoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jChkBiometricoStateChanged
        //Habilita/Desabilita campos
        if (jChkBiometrico.isSelected()) {
            jChkIdentificacao.setEnabled(true);
            jChkVerificacao.setEnabled(true);
            jChkListaBio.setEnabled(true);
        } else {
            jChkVerificacao.setEnabled(false);
            jChkIdentificacao.setEnabled(false);
            jChkListaBio.setEnabled(false);
            jChkVerificacao.setSelected(false);
            jChkIdentificacao.setSelected(false);
            jChkListaBio.setSelected(false);
        }
    }//GEN-LAST:event_jChkBiometricoStateChanged

    private void jCboEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboEquipamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCboEquipamentoActionPerformed

    private void jCboEquipamentocboequipamento_itemstatechanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboEquipamentocboequipamento_itemstatechanged
        //Combo Equipamento
        //Carrega as opções de acordo com a seleção

        //Se catraca
        if ((jCboEquipamento.getSelectedIndex() != Enumeradores.Acionamento_Coletor)) {
            jOptEsquerda.setEnabled(true);
            jOptDireita.setEnabled(true);
            jChkDoisLeitores.setEnabled(true);

            //Se Urna
            if ((jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Urna)) {
                jOptDireita.setSelected(true);
                jOptEsquerda.setEnabled(false);
                jOptDireita.setEnabled(false);
                imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/direita-java.JPG"))); // NOI18N
                lblCatraca.setEnabled(true);
                jCboTipoLeitor.setSelectedIndex(4);//proximidade
                jChkDoisLeitores.setSelected(true);
            } else { //Não é Urna
                if (jOptDireita.isSelected()) {
                    imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/direita-java.JPG"))); // NOI18N
                } else {
                    if (jOptEsquerda.isSelected()) {
                        imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/esquerda-java.JPG"))); // NOI18N
                    }
                }
                lblCatraca.setEnabled(true);
            }
        } else { //Coletor
            jOptEsquerda.setEnabled(false);
            jOptDireita.setEnabled(false);
            lblCatraca.setEnabled(false);
            imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/nenhum.JPG"))); // NOI18N
        }
    }//GEN-LAST:event_jCboEquipamentocboequipamento_itemstatechanged

    private void jCboTipoLeitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCboTipoLeitorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jCboTipoLeitorMouseClicked

    /**
     * Metodo responsável por realizar um comando simples com o equipamento para
     * detectar se esta conectado.
     *
     * @param Inner
     * @return
     */
    private Integer testarConexaoInner(Integer Inner) {
        int[] DataHora = new int[6];
        Integer ret = easyInner.ReceberRelogio(Inner, DataHora);
        return ret;
    }

    /**
     * CONECTAR Inicia a conexão com o Inner Próximo passo:
     * ESTADO_ENVIAR_CFG_OFFLINE
     */
    private void PASSO_ESTADO_CONECTAR() {
        try 
        {
            long IniConexao = 0;
            long tempo = 0;

            Ret = Enumeradores.Limpar;
            //Inicia tempo ping online
            typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = (int) System.currentTimeMillis();

            //Mensagem Status
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Conectando...");

            IniConexao = System.currentTimeMillis();
            //Realiza loop enquanto o tempo fim for menor que o tempo atual, e o comando retornado diferente de OK.
            do {
                tempo = System.currentTimeMillis() - IniConexao;
                //Tenta abrir a conexão 
                Thread.sleep(10l);
                Ret = testarConexaoInner(typInnersCadastrados[lngInnerAtual].Numero);

            } while (Ret != Enumeradores.RET_COMANDO_OK && tempo < 10000);

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                DefineVersao();
                //caso consiga o Inner vai para o Passo de Configuração OFFLINE, posteriormente para coleta de Bilhetes.
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_CFG_OFFLINE;

                jBtnParar.setEnabled(true);

            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }

        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
        }
    }

    /**
     * Passo responsável pelo envio das mensagens off-line para o Inner
     */
    private void PASSO_ESTADO_ENVIAR_MSG_OFFLINE() {
        try 
        {    
            if(!jOptEsquerda.isSelected())
            {
                //Mensagem Entrada e Saida Offline Liberado!
                easyInner.DefinirMensagemEntradaOffLine(1, "Entrada liberada.");
                easyInner.DefinirMensagemSaidaOffLine(1, "Saida liberada.");            
            }
            else
            {
                //Mensagem Entrada e Saida Offline Liberado!
                easyInner.DefinirMensagemEntradaOffLine(1, "Saida liberada.");
                easyInner.DefinirMensagemSaidaOffLine(1, "Entrada liberada.");
            }

            easyInner.DefinirMensagemPadraoOffLine(1, "Modo OffLine");

            Ret = easyInner.EnviarMensagensOffLine(typInnersCadastrados[lngInnerAtual].Numero);

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_CONFIGMUD_ONLINE_OFFLINE;
                typInnersCadastrados[lngInnerAtual].TempoColeta = (int) System.currentTimeMillis() + 3000;
            } else {
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Configura a mudança automática Habilita/Desabilita a mudança automática
     * do modo OffLine do Inner para OnLine e vice-versa. Habilita a mudança
     * Offline
     */
    private void PASSO_ESTADO_ENVIAR_CONFIGMUD_ONLINE_OFFLINE() {

        try {
            String TipoComunicacao = jCboTipoConexao.getSelectedItem().toString();

            int Posicao = TipoComunicacao.indexOf("TCP");
            if (Posicao != -1) {
                //Habilita a mudança Offline
                easyInner.HabilitarMudancaOnLineOffLine(2, 20);
            } else {
                //Habilita a mudança Offline
                easyInner.HabilitarMudancaOnLineOffLine(1, 20);
            }

            //Configura o teclado para quando o Inner voltar para OnLine após uma queda
            //para OffLine.
            easyInner.DefinirConfiguracaoTecladoOnLine(typInnersCadastrados[lngInnerAtual].QtdDigitos, 1, 5, 17);

            //Define Mudanças OnLine
            //Função que configura BIT a BIT, Ver no manual Anexo III
            easyInner.DefinirEntradasMudancaOnLine(ConfiguraEntradasMudancaOnLine(InnerAtual));

            if (typInnersCadastrados[lngInnerAtual].Biometrico) {
                // Configura entradas mudança OffLine com Biometria
                easyInner.DefinirEntradasMudancaOffLineComBiometria((typInnersCadastrados[lngInnerAtual].Teclado ? Enumeradores.Opcao_SIM : Enumeradores.Opcao_NAO), 3, (byte) (typInnersCadastrados[lngInnerAtual].DoisLeitores ? 3 : 0), typInnersCadastrados[lngInnerAtual].Verificacao, typInnersCadastrados[lngInnerAtual].Identificacao);
            } else {
                // Configura entradas mudança OffLine
                easyInner.DefinirEntradasMudancaOffLine((typInnersCadastrados[lngInnerAtual].Teclado ? Enumeradores.Opcao_SIM : Enumeradores.Opcao_NAO), (byte) (typInnersCadastrados[lngInnerAtual].DoisLeitores ? 1 : 3), (byte) (typInnersCadastrados[lngInnerAtual].DoisLeitores ? 2 : 0), 0);
            }
            
 
            //Define mensagem de Alteração Online -> Offline.
            easyInner.DefinirMensagemPadraoMudancaOffLine(1, " Modo OffLine");

            //Define mensagem de Alteração OffLine -> OnLine.
            easyInner.DefinirMensagemPadraoMudancaOnLine(1, "Modo Online");

            //Envia Configurações.
            Ret = easyInner.EnviarConfiguracoesMudancaAutomaticaOnLineOffLine(typInnersCadastrados[lngInnerAtual].Numero);

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_COLETAR_BILHETES;
                typInnersCadastrados[lngInnerAtual].TempoColeta = (int) System.currentTimeMillis() + 3000;
                typInnersCadastrados[lngInnerAtual].TentativasColeta = 0;
            } else {
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Passo responsável pela criação e envio da lista de horários de acesso.
     *
     */
    private void PASSO_ESTADO_ENVIAR_HORARIOS() {
        try {
            if (jChkHorarios.isSelected()) {

                MontarHorarios();

                Ret = easyInner.EnviarHorariosAcesso(typInnersCadastrados[lngInnerAtual].Numero);

                if (Ret == Enumeradores.RET_COMANDO_OK) {
                    typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_USUARIOS_LISTAS;
                    typInnersCadastrados[lngInnerAtual].TempoColeta = (int) System.currentTimeMillis() + 3000;
                } else {
                    if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                        typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                    }
                    typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
                }
            } else {
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_USUARIOS_LISTAS;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Passo responsável pelo envio da lista usuário com acesso off-line
     */
    private void PASSO_ESTADO_ENVIAR_USUARIOS_LISTAS() {
        try {
            if (typInnersCadastrados[lngInnerAtual].Lista) {

                //Define a Lista de verificação
                if (jCboPadraoCartao.getSelectedIndex() == 1) {
                    MontarListaLivre();
                } 
                else
                {
                    MontarListaTopdata();
                }
                
                //Define qual tipo de lista(controle) de acesso o Inner vai utilizar.
                //Utilizar lista branca (cartões fora da lista tem o acesso negado).
                easyInner.DefinirTipoListaAcesso(1);
            } else {
                //Não utilizar a lista de acesso.
                easyInner.DefinirTipoListaAcesso(0);
            }

            if (typInnersCadastrados[lngInnerAtual].ListaBio) {
                //Chama rotina que monta o buffer de cartoes que nao irao precisar da digital
                MontarBufferListaSemDigital();
                if (InnerAtual.InnerNetAcesso)
                {
                    //Envia o buffer com a lista de usuarios sem digital
                    Ret = easyInner.EnviarListaUsuariosSemDigitalBioVariavel(Integer.parseInt(jSpnNumInner.getValue().toString()), InnerAtual.QtdDigitos);
                }
                else
                {
                    //Envia o buffer com a lista de usuarios sem digital
                    Ret = easyInner.EnviarListaUsuariosSemDigitalBio(Integer.parseInt(jSpnNumInner.getValue().toString()));
                }
            }
            
            if (jChkHorarios.isSelected())
            {
                MontarHorarios();
                easyInner.DefinirTipoListaAcesso(1);
            }

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MENSAGEM;
                typInnersCadastrados[lngInnerAtual].TempoColeta = (int) System.currentTimeMillis() + 3000;
            } else {
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Passo responsável pelo envio das configurações off-line do Inner
     */
    private void PASSO_ESTADO_ENVIAR_CFG_OFFLINE() {
        try {
            //Mensagem Status
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Enviado configurações OFF-LINE...");

            //Preenche os campos de configuração do Inner
            MontaConfiguracaoInner(Enumeradores.MODO_OFF_LINE);
            
            //Envia o comando de configuração
            Ret = easyInner.EnviarConfiguracoes(typInnersCadastrados[lngInnerAtual].Numero);

            //Testa o retorno do envio das configurações Off Line
            if (Ret == Enumeradores.RET_COMANDO_OK) {

                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                //verifica se o enviar lista esta selecionado
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_HORARIOS;
                typInnersCadastrados[lngInnerAtual].TempoColeta = (int) System.currentTimeMillis() + 3000;

            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Passo agurada 2 segundos o tempo para apresentação da mensagem no visor
     * do Inner
     */
    private void PASSO_AGUARDA_TEMPO_MENSAGEM() {
        try {
            //Após passar os 2 segundos volta para o passo enviar mensagem padrão
            long tempo = (int)System.currentTimeMillis() - typInnersCadastrados[lngInnerAtual].TempoInicialMensagem;
            if (tempo > 2000) {
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_PADRAO;
                easyInner.DesligarLedVermelho(typInnersCadastrados[lngInnerAtual].Numero);
            }
        } 
        catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Passo resposável pela coleta dos bilhetes registrados em modo off-line
     */
    private void PASSO_ESTADO_COLETAR_BILHETES() throws InterruptedException {

        if (typInnersCadastrados[lngInnerAtual].InnerNetAcesso) {
            ColetarBilhetesInnerAcesso();
        } else {
            ColetarBilhetesInnerNet();
        }
        typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_CFG_ONLINE;

    }

    /**
     * Realiza a coleta dos bilhetes off line do equipamentos da linha Inner
     * Acesso
     *
     * @throws InterruptedException
     */
    private void ColetarBilhetesInnerAcesso() throws InterruptedException {
        int[] Bilhete = new int[8];
        StringBuffer Cartao;
        int nBilhetes;
        int i = Enumeradores.Limpar;
        int QtdeBilhetes;
        int receber[] = new int[2];

        //Verifica conexao
        nBilhetes = 0;
        QtdeBilhetes = 0;
        Ret = easyInner.ReceberQuantidadeBilhetes(typInnersCadastrados[lngInnerAtual].Numero, receber);
        QtdeBilhetes = receber[0];

        do {
            if (QtdeBilhetes > 0) {
                do {
                    Thread.sleep(100l);

                    Cartao = new StringBuffer();
                    //Coleta um bilhete Off-Line que está armazenado na memória do Inner
                    Ret = easyInner.ColetarBilhete(typInnersCadastrados[lngInnerAtual].Numero, Bilhete, Cartao);
                    if (Ret == Enumeradores.RET_COMANDO_OK) {

                        //Armazena os dados do bilhete no list, pode ser utilizado com
                        //banco de dados ou outro meio de armazenamento compatível
                        jTxaBilhetes.append("Tipo:" + String.valueOf(Bilhete[0]) + " Cartão:"
                                + Cartao.toString() + " Data:"
                                + (String.valueOf(Bilhete[1]).length() == 1 ? "0" + String.valueOf(Bilhete[1]) : String.valueOf(Bilhete[1])) + "/"
                                + (String.valueOf(Bilhete[2]).length() == 1 ? "0" + String.valueOf(Bilhete[2]) : String.valueOf(Bilhete[2])) + "/"
                                + String.valueOf(Bilhete[3]) + " Hora:"
                                + (String.valueOf(Bilhete[4]).length() == 1 ? "0" + String.valueOf(Bilhete[4]) : String.valueOf(Bilhete[4])) + ":"
                                + (String.valueOf(Bilhete[5]).length() == 1 ? "0" + String.valueOf(Bilhete[5]) : String.valueOf(Bilhete[5])) + ":"
                                + (String.valueOf(Bilhete[6]).length() == 1 ? "0" + String.valueOf(Bilhete[6]) : String.valueOf(Bilhete[6])) + "\n");

                        nBilhetes++;
                        QtdeBilhetes--;
                    }

                } while (QtdeBilhetes > 0);

                jLblStatus.setText("Foram coletados " + nBilhetes + " bilhete(s) offline !");
                Ret = easyInner.ReceberQuantidadeBilhetes(typInnersCadastrados[lngInnerAtual].Numero, receber);
                QtdeBilhetes = receber[0];
            }
        } while (QtdeBilhetes > 0);
    }

    /**
     * Realiza a coleta dos bilhetes off line do equipamentos da linha Inner Net
     */
    private void ColetarBilhetesInnerNet() {
        try {
            int[] Bilhete = new int[8];
            StringBuffer Cartao;
            Long tempo;

            //Exibe no rodapé da janela o estado da maquina.
            jLblStatus.setText("Inner " + Integer.toString(typInnersCadastrados[lngInnerAtual].Numero) + " Coletando Bilhetes...");

            tempo = System.currentTimeMillis() + 200;
            do {
                Cartao = new StringBuffer();
                //Envia o Comando de Coleta de Bilhetes..
                Ret = easyInner.ColetarBilhete(typInnersCadastrados[lngInnerAtual].Numero, Bilhete, Cartao);

                //Zera a contagem de Ping Online.
                typInnersCadastrados[lngInnerAtual].CntDoEvents = 0;
                typInnersCadastrados[lngInnerAtual].CountPingFail = 0;
                typInnersCadastrados[lngInnerAtual].CountRepeatPingOnline = 0;

                //Caso exista bilhete a coletar..
                if (Ret == Enumeradores.RET_COMANDO_OK) {

                    //Recebe hora atual para inicio do PingOnline
                    typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = (int) System.currentTimeMillis();

                    //Adiciona a lista de bilhetes o Nro bilhete coletado..
                    jTxaBilhetes.append("Marcações Offline. Inner: " + typInnersCadastrados[lngInnerAtual].Numero + " Complemento:"
                            + String.valueOf(Bilhete[0]) + " Data:"
                            + (String.valueOf(Bilhete[1]).length() == 1 ? "0" + String.valueOf(Bilhete[1]) : String.valueOf(Bilhete[1])) + "/"
                            + (String.valueOf(Bilhete[2]).length() == 1 ? "0" + String.valueOf(Bilhete[2]) : String.valueOf(Bilhete[2])) + "/"
                            + String.valueOf(Bilhete[3]) + " Hora:"
                            + (String.valueOf(Bilhete[4]).length() == 1 ? "0" + String.valueOf(Bilhete[4]) : String.valueOf(Bilhete[4])) + ":"
                            + (String.valueOf(Bilhete[5]).length() == 1 ? "0" + String.valueOf(Bilhete[5]) : String.valueOf(Bilhete[5])) + ":"
                            + (String.valueOf(Bilhete[6]).length() == 1 ? "0" + String.valueOf(Bilhete[6]) : String.valueOf(Bilhete[6])) + " "
                            + " Cartão: " + Cartao.toString() + "\n");

                }
            } while (System.currentTimeMillis() < tempo);
            typInnersCadastrados[lngInnerAtual].TentativasColeta += 1;
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }

    }

    /**
     * Configura modo On-line Próximo passo: ESTADO_ENVIAR_DATA_HORA
     */
    private void PASSO_ESTADO_ENVIAR_CFG_ONLINE() {
        try {

            //Monta configuração modo Online
            MontaConfiguracaoInner(Enumeradores.MODO_ON_LINE);

            //Envia as configurações ao Inner Atual.
            Ret = easyInner.EnviarConfiguracoes(typInnersCadastrados[lngInnerAtual].Numero);

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //caso consiga enviar as configurações, passa para o passo Enviar Data Hora
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_DATA_HORA;
            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Envia ao Inner data e hora atual Próximo passo: ESTADO_ENVIAR_MSG_PADRAO
     */
    private void PASSO_ESTADO_ENVIAR_DATA_HORA() {
        try {
            //Exibe estado do Inner no Rodapé da Janela
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Enviando data e hora...");

            //Declaração de Variáveis..
            Ret = Enumeradores.Limpar;

            Date Data = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yy");
            int Ano = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("MM");
            int Mes = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("dd");
            int Dia = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("HH");
            int Hora = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("mm");
            int Minuto = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("ss");
            int Segundo = Integer.parseInt(formatter.format(Data));
            //Envia Comando de Relógio ao Inner Atual..
//          RelogioInner relogioInner = new RelogioInner();
            Ret = easyInner.EnviarRelogio(typInnersCadastrados[lngInnerAtual].Numero, Dia, Mes, Ano, Hora, Minuto, Segundo);
            //Testa o Retorno do comando de Envio de Relógio..
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //Vai para o passo de Envio de Msg Padrão..
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_PADRAO;
            } 
            else 
            {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) 
                {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Envia mensagem padrão modo Online Próximo passo:
     * ESTADO_CONFIGURAR_ENTRADAS_ONLINE
     */
    private void PASSO_ENVIAR_MENSAGEM_PADRAO() {
        try {
            //Exibe estado do Inner no Rodapé da Janela
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Enviando Mensagem Padrão...");

            //Declaração de Variáveis..
            Ret = Enumeradores.Limpar;

            //Envia comando definindo a mensagem Padrão Online para o Inner.
            Ret = easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 1, "   Modo Online");

            //Testa o retorno da mensagem enviada..
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //Muda o passo para configuração de entradas Online.
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONFIGURAR_ENTRADAS_ONLINE;
            } 
            else 
            {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) 
                {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                //Adiciona 1 ao contador de tentativas
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Preparação configuração online para entrar em modo Polling Próximo passo:
     * ESTADO_POLLING
     */
    private void PASSO_ESTADO_CONFIGURAR_ENTRADAS_ONLINE() {
        try {
            //Exibe estado do Inner no Rodapé da Janela
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Configurando Entradas Online...");

            //Declaração de variáveis..
            Ret = Enumeradores.Limpar;

            //Converte Binário para Decimal
            int ValorDecimal = ConfiguraEntradasMudancaOnLine(InnerAtual); //Ver no manual Anexo III

            Ret = easyInner.EnviarFormasEntradasOnLine(typInnersCadastrados[lngInnerAtual].Numero, (byte) typInnersCadastrados[lngInnerAtual].QtdDigitos, 1, (byte) ValorDecimal, 15, 17);
            //Testa o retorno do comando..
            if (Ret ==  Enumeradores.RET_COMANDO_OK) {
                //Vai para o Estado De Polling.
                typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = (int) System.currentTimeMillis();
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_POLLING;

                if (typInnersCadastrados[lngInnerAtual].Catraca) {
                    jBtnEntrada.setText("Entrada");
                    jBtnSaida.setText("Saída");
                    jBtnEntrada.setEnabled(true);
                    jBtnSaida.setEnabled(true);
                } else {
                    jBtnEntrada.setText("Porta 1");
                    jBtnSaida.setText("Porta 2");
                    jBtnEntrada.setEnabled(true);
                    jBtnSaida.setEnabled(true);
                }

            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                //Adiciona 1 ao contador de tentativas
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Verifica se a quantidade maxima de tentativas de um envio de comando
     * Ocorreu, caso tenha ocorrido retorna TRUE, senão FALSE..
     *
     * @return
     */
    private static boolean MaximoNumeroTentativas() {
        //Incrementa o número de tentativas..
        intTentativas = intTentativas + 1;

        //Verifica se o número de tentativas é maior do que 3..
        //MAXIMO_TENTATIVAS_COMUNICACAO
        if (intTentativas > 2) {
            return true; //Retorna TRUE
        } else {
            return false; //Retorna FALSE
        }
    }

    /**
     * Mostra mensagem para que seja informado se é entrada ou saída Este estado
     * configura a mensagem padrão que será exibida no dispositivo em seu
     * funcionamento Online utilizando o método EnviarMensagemPadraoOnline. O
     * passo posterior a este estado é o passo de configuração de entradas
     * online, ou em caso de erro pode retornar para o estado de conexão após
     * alcançar o número máximo de tentativas. Próximo passo: ESTADO_POLLING
     */
    private void PASSO_ESTADO_DEFINICAO_TECLADO() {
        int Ret = Enumeradores.Limpar;

        //Envia mensagem Padrão Online..
        easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "ENTRADA OU SAIDA?");
        Ret = easyInner.EnviarFormasEntradasOnLine(typInnersCadastrados[lngInnerAtual].Numero,
                0, //Quantidade de Digitos do Teclado.. (Não aceita digitação numérica)
                0, //0 – não ecoa
                Enumeradores.ACEITA_TECLADO,
                10, // Tempo de entrada do Teclado (10s).
                32);//Posição do Cursor (32 fica fora..)

        //Se Retorno OK, vai para proximo estado..
        if (Ret == Enumeradores.RET_COMANDO_OK) {
            intTentativas = 0;
            typInnersCadastrados[lngInnerAtual].EstadoTeclado = Enumeradores.EstadosTeclado.AGUARDANDO_TECLADO;
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_AGUARDA_DEFINICAO_TECLADO;
        } else {
            //Caso o retorno não for OK, tenta novamente até 3x..
            if (MaximoNumeroTentativas() == true) {
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
            }
        }
    }

    /**
     * PASSO_ESTADO_AGUARDAR_DEFINICAO_TECLADO Aguarda a resposta do teclado
     * (Entrada, Saida, anula ou confirma) Proximo estado: ESTADO_POLLING
     */
    private void PASSO_ESTADO_AGUARDA_DEFINICAO_TECLADO() {
        try {
            int[] Bilhete = new int[8];
            StringBuffer Cartao;
            String NumCartao;
            String Bilhetee;

            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Estado aguardar definição teclado");

            Cartao = new StringBuffer();
            NumCartao = new String();

            //Envia o Comando de Coleta de Bilhetes..
            Ret = easyInner.ReceberDadosOnLine(typInnersCadastrados[lngInnerAtual].Numero, Bilhete, Cartao);

            //Atribui Temporizador
            typInnersCadastrados[lngInnerAtual].Temporizador = (int) System.currentTimeMillis();

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                if (typInnersCadastrados[lngInnerAtual].EstadoTeclado == Enumeradores.EstadosTeclado.AGUARDANDO_TECLADO) {
                    //****************************************************
                    //Entrada, saída liberada, confirma, anula ou função tratar mensagem
                    //66 - "Entrada" via teclado
                    //67 - "Saída" via teclado
                    //35 - "Confirma" via teclado
                    //42 - "Anula" via teclado
                    //65 - "Função" via teclado
                    if (Integer.parseInt(String.valueOf(Bilhete[1])) == Enumeradores.TECLA_ENTRADA) //entrada
                    {
                        easyInner.AcionarBipCurto(typInnersCadastrados[lngInnerAtual].Numero);
                        habilitaLadoCatraca("Entrada");
                        typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                    } 
                    else if (Integer.parseInt(String.valueOf(Bilhete[1])) == Enumeradores.TECLA_SAIDA) //saida
                    {
                        easyInner.AcionarBipCurto(typInnersCadastrados[lngInnerAtual].Numero);
                        habilitaLadoCatraca("Saida");
                        typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                    } 
                    else if (Integer.parseInt(String.valueOf(Bilhete[1])) == Enumeradores.TECLA_CONFIRMA) //confirma
                    {
                        easyInner.AcionarBipCurto(typInnersCadastrados[lngInnerAtual].Numero);
                        Ret = easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "LIBERADO DOIS   SENTIDOS.");
                        typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                    } else if (Integer.parseInt(String.valueOf(Bilhete[1])) == Enumeradores.TECLA_ANULA) //anula
                    {
                        easyInner.LigarBackLite(typInnersCadastrados[lngInnerAtual].Numero);
                        typInnersCadastrados[lngInnerAtual].TempoInicialMensagem = (int) System.currentTimeMillis();
                        typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                        typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_PADRAO;
                    } else if (Integer.parseInt(String.valueOf(Bilhete[1])) == Enumeradores.TECLA_FUNCAO) //função
                    {
                        typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_DEFINICAO_TECLADO;
                    }
                    typInnersCadastrados[lngInnerAtual].EstadoTeclado = Enumeradores.EstadosTeclado.TECLADO_EM_BRANCO;
                }
            } else {
                long temp = System.currentTimeMillis() - typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine;
                //Se passar 3 segundos sem receber nada, passa para o estado enviar ping on line, para manter o equipamento em on line.
                if ((int) temp > 3000) {
                    typInnersCadastrados[lngInnerAtual].EstadoSolicitacaoPingOnLine = typInnersCadastrados[lngInnerAtual].EstadoAtual;
                    typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                    typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = (int) System.currentTimeMillis();
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.PING_ONLINE;
                }
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * MENSAGEM_PADRAO Envia mensagem acesso negado Próximo passo:
     * AGUARDA_TEMPO_MENSAGEM
     */
    private void PASSO_ENVIAR_MENSAGEM_ACESSO_NEGADO() {
        try {
            //Testa o Retorno do comando de Envio de Mensagem Padrão On Line
            if (easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, " Acesso Negado!  NAO CADASTRADO \r\n") == Enumeradores.RET_COMANDO_OK) {
                typInnersCadastrados[lngInnerAtual].TempoInicialMensagem = System.currentTimeMillis();
                easyInner.AcionarBipLongo(typInnersCadastrados[lngInnerAtual].Numero);
                //if (typInnersCadastrados[lngInnerAtual].InnerNetAcesso) {
                easyInner.LigarLedVermelho(typInnersCadastrados[lngInnerAtual].Numero);
                //}
                //Muda o passo para configuração de entradas Online.
                typInnersCadastrados[lngInnerAtual].TempoInicialMensagem= (int) System.currentTimeMillis();
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.AGUARDA_TEMPO_MENSAGEM;
            } 
            else 
            {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                //Adiciona 1 ao contador de tentativas
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Envia mensagem padrão estado Urna Próximo passo: ESTADO_MONITORA_URNA
     */
    private void PASSO_ESTADO_ENVIA_MSG_URNA() {
        try {
            //Testa o Retorno do comando de Envio de Mensagem Padrão On Line
            if (easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, " DEPOSITE O       CARTAO") == Enumeradores.RET_COMANDO_OK) {
                easyInner.AcionarRele2(typInnersCadastrados[lngInnerAtual].Numero);
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_MONITORA_URNA;
            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Monitora o depósito do cartão na Urna Próximo passo:
     * ESTADO_LIBERAR_CATRACA
     */
    private void PASSO_ESTADO_MONITORA_URNA() {
        try {
            //Exibe estado do giro
            jLblDados.setText("Monitorando Giro de Catraca!");

            //Exibe estado do Inner no Rodapé da Janela
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Monitora Giro da Catraca...");

            //Declaração de Variáveis..
            int[] Bilhete = new int[8];
            StringBuffer Cartao;
            String NumCartao;
            int Ret = Enumeradores.Limpar;

            Cartao = new StringBuffer();
            NumCartao = new String();

            //Monitora o giro da catraca..
            Ret = easyInner.ReceberDadosOnLine(typInnersCadastrados[lngInnerAtual].Numero, Bilhete, Cartao);

            //Testa o retorno do comando..
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //Testa se girou o não a catraca..
                if (Bilhete[0] == Enumeradores.URNA) {
                    jLblDados.setText("URNA RECOLHEU CARTÃO");
                    //Vai para o estado de Envio de Msg Padrão..
                    LiberaSaida = true;
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                } else if (Bilhete[0] == Enumeradores.FIM_TEMPO_ACIONAMENTO) {
                    jLblDados.setText("NÃO DEPOSITOU CARTÃO");
                    //easyInner.AcionarBipLongo(typInnersCadastrados[lngInnerAtual].Numero);
                    easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "     ACESSO          NEGADO");
                    //Vai para o estado de Envio de Msg Padrão..
                    typInnersCadastrados[lngInnerAtual].TempoInicialMensagem = (int) System.currentTimeMillis();
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.AGUARDA_TEMPO_MENSAGEM;
                }else if (Bilhete[0] == Enumeradores.URNA_CHEIA) {
                    
                    jLblDados.setText("URNA CHEIA");
                    easyInner.AcionarBipLongo(typInnersCadastrados[lngInnerAtual].Numero);

                    //Vai para o estado de Envio de Msg Padrão..
                    typInnersCadastrados[lngInnerAtual].TempoInicialMensagem = (int) System.currentTimeMillis();
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_URNA_CHEIA;
                }
                
            } else {
                //Caso o tempo que estiver monitorando o giro chegue a 3 segundos,
                long tempo = (System.currentTimeMillis() - typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine);
                //deverá enviar o ping on line para manter o equipamento em modo on line
                if (tempo > 3000) {
                    typInnersCadastrados[lngInnerAtual].EstadoSolicitacaoPingOnLine = typInnersCadastrados[lngInnerAtual].EstadoAtual;
                    typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                    typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = (int) System.currentTimeMillis();
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.PING_ONLINE;
                }
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }
    private void PASSO_ESTADO_VALIDA_URNA_CHEIA(){
        //Declaração de Variáveis..
        easyInner.AcionarRele2(typInnersCadastrados[lngInnerAtual].Numero);
        int[] Bilhete = new int[8];
        StringBuffer Cartao;
        String NumCartao;
        int Ret = Enumeradores.Limpar;

        Cartao = new StringBuffer();
        NumCartao = new String();

        //Monitora para ver se a Urna esta cheia
        Ret = easyInner.ReceberDadosOnLine(typInnersCadastrados[lngInnerAtual].Numero, Bilhete, Cartao);
        
        //Testa o retorno do comando.. 
        if (Ret == Enumeradores.RET_COMANDO_OK) {
            //Testa se a urna esta cheia
            if (Bilhete[0] == Enumeradores.URNA_CHEIA) {
                jLblDados.setText("URNA CHEIA");
                easyInner.AcionarBipLongo(typInnersCadastrados[lngInnerAtual].Numero);
                //Vai para o estado de Envio de Msg Urna cheia
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_URNA_CHEIA;
            }
        }
        else{
            System.out.println("ESTADO_ENVIA_MSG_URNA");
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIA_MSG_URNA;
        }
    }
    
    private void PASSO_ESTADO_ENVIAR_MSG_URNA_CHEIA(){
        int Ret = Enumeradores.Limpar;
        
                
        Ret = easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "   URNA CHEIA    ESVAZIAR URNA ");
        
        if  (Ret ==  Enumeradores.RET_COMANDO_OK){
            
            if (typInnersCadastrados[lngInnerAtual].InnerNetAcesso) {
                easyInner.LigarLedVermelho(typInnersCadastrados[lngInnerAtual].Numero);
            }
            
            typInnersCadastrados[lngInnerAtual].TempoInicialMensagem = (int) System.currentTimeMillis();
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.AGUARDA_TEMPO_MENSAGEM;
        }
        else{
            //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
            if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
            }
            //Adiciona 1 ao contador de tentativas
            typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
        }

    }

    /**
     * De acordo com o que foi informado (Esquerda ou Direita)F
     *
     * @param lado
     */
    private void habilitaLadoCatraca(String lado) {
        if (lado.equals("Entrada")) {
            //entrada
            if (jOptDireita.isSelected()) {
                LiberaEntrada = true;
                LiberaEntradaInvertida = false;
            } else {
                LiberaEntradaInvertida = true;
                LiberaEntrada = false;
            }
        }

        if (lado.equals("Saida")) {
            //saída
            if (jOptDireita.isSelected()) {
                LiberaSaida = true;
                LiberaSaidaInvertida = false;
            } else {
                LiberaSaidaInvertida = true;
                LiberaSaida = false;
            }
        }
    }

    /**
     * Método responsável pela liberação de acesso. Somente usuarios listado
     * serão liberados. Esta consulta deverá ser feita em sua base de dados.
     *
     * @param NumCartao
     * @return
     */
    private boolean LiberaAcesso(String NumCartao) 
    {
        try
        {
            List<Usuarios> listaCartao  = DAOUsuarios.consultarUsuarios(0);
            boolean ret                 = false;
            
            /*Finding if the card is included at the listOffLine, it can be 
            Online list too*/
            for (int i=0 ;listaCartao.size() >i; i++) 
            {
                String slCartaoSZero = remZeroEsquerda(listaCartao.get(i).getUsuario());
                if (slCartaoSZero.equals(NumCartao.trim())) 
                {
                    if(listaCartao.get(i).getFaixa()==101)
                    {
                        ret =  true;
                        break;
                    }
                    else
                    {
                        ret = false;
                        break;
                    }        
                }
            }
            return ret;
        }
        catch(IOException | SQLException | ClassNotFoundException e)
        {
            return false;
        }
    }



    /**
     * Aciona o rele do Inner 1
     */
    private void ACIONAR_RELE() {
        easyInner.AcionarBipCurto(1);
        if (Inner.rele) {
            easyInner.AcionarRele1(1);
        } else {
            easyInner.AcionarRele2(1);
        }
        typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_POLLING;
    }

    /**
     * Libera a catraca de acordo com o lado informado Próximo Passo:
     * ESTADO_MONITORA_GIRO_CATRACA
     */
    private void PASSO_LIBERA_GIRO_CATRACA() {
        try {
            //Exibe estado do Inner no Rodapé da Janela
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Libera Giro da Catraca...");
            //Declaração de Variáveis..
            Ret = Enumeradores.Limpar;
            //Envia comando de liberar a catraca para Entrada.
            if (LiberaEntrada) {
                easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "                ENTRADA LIBERADA");
                LiberaEntrada = false;
                Ret = easyInner.LiberarCatracaEntrada(typInnersCadastrados[lngInnerAtual].Numero);
            } else if (LiberaEntradaInvertida) {
                easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "                ENTRADA LIBERADA");
                LiberaEntradaInvertida = false;
                Ret = easyInner.LiberarCatracaEntradaInvertida(typInnersCadastrados[lngInnerAtual].Numero);
            } else if (LiberaSaida) {
                //Envia comando de liberar a catraca para Saída.
                easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "                SAIDA LIBERADA");
                LiberaSaida = false;
                Ret = easyInner.LiberarCatracaSaida(typInnersCadastrados[lngInnerAtual].Numero);
            } else if (LiberaSaidaInvertida) {
                easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "                SAIDA LIBERADA");
                LiberaSaidaInvertida = false;
                Ret = easyInner.LiberarCatracaSaidaInvertida(typInnersCadastrados[lngInnerAtual].Numero);
            } else {
                easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "LIBERADO DOIS SENTIDOS");
                Ret = easyInner.LiberarCatracaDoisSentidos(typInnersCadastrados[lngInnerAtual].Numero);
            }

            //Testa Retorno do comando..
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                easyInner.AcionarBipCurto(typInnersCadastrados[lngInnerAtual].Numero);
                typInnersCadastrados[lngInnerAtual].CountPingFail = 0;
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = (int) System.currentTimeMillis();
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_MONITORA_GIRO_CATRACA;
            } else {
                //Se o retorno for diferente de 0 tenta liberar a catraca 3 vezes, caso não consiga enviar o comando volta para o passo reconectar.
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Verifica se a catraca foi girada ou não e caso sim para qual lado.
     * Próximo Passo: ESTADO_ENVIAR_MSG_PADRAO
     */
    private void PASSO_MONITORA_GIRO_CATRACA() {
        try {
            int[] Bilhete = new int[8];
            StringBuffer Cartao;

            Cartao = new StringBuffer();

            //Exibe estado do giro
            jLblDados.setText("Monitorando Giro de Catraca!");

            //Exibe estado do Inner no Rodapé da Janela
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Monitora Giro da Catraca...");

            //Monitora o giro da catraca..
            Ret = easyInner.ReceberDadosOnLine(typInnersCadastrados[lngInnerAtual].Numero, Bilhete, Cartao);

            //Testa o retorno do comando..
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //Testa se girou o não a catraca..
                if (Bilhete[0] == Enumeradores.FIM_TEMPO_ACIONAMENTO) {
                    jLblDados.setText("Não girou a catraca!");
                } else if (Bilhete[0] == (int) Enumeradores.GIRO_DA_CATRACA_TOPDATA) {
                    if (jOptEsquerda.isSelected()) {
                        if (Integer.parseInt(String.valueOf(Bilhete[1])) == 0) {
                            jLblDados.setText("Girou a catraca para saída.");
                        } else {
                            jLblDados.setText("Girou a catraca para entrada.");
                        }
                    } else {
                        if (Integer.parseInt(String.valueOf(Bilhete[1])) == 0) {
                            jLblDados.setText("Girou a catraca para entrada.");
                        } else {
                            jLblDados.setText("Girou a catraca para saída.");
                        }
                    }
                }

                jBtnEntrada.setEnabled(true);
                jBtnSaida.setEnabled(true);

                //Vai para o estado de Envio de Msg Padrão..
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_PADRAO;
            } else {
                //Caso o tempo que estiver monitorando o giro chegue a 3 segundos,
                //deverá enviar o ping on line para manter o equipamento em modo on line
                long tempo = (System.currentTimeMillis() - typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine);
                //Se passar 3 segundos sem receber nada, passa para o estado enviar ping on line, para manter o equipamento em on line.
                if (tempo >= 3000) {
                    typInnersCadastrados[lngInnerAtual].EstadoSolicitacaoPingOnLine = typInnersCadastrados[lngInnerAtual].EstadoAtual;
                    typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                    typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = System.currentTimeMillis();
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.PING_ONLINE;
                }
            }
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Testa comunicação com o Inner e mantém o Inner em OnLine quando a mudança
     * automática está configurada. Especialmente indicada para a verificação da
     * conexão em comunicação TCP/IP. Próximo Passo: RETORNA MÉTODO QUE O
     * ACIONOU
     */
    private void PASSO_ESTADO_ENVIA_PING_ONLINE() {
        try {
            //Exibe estado do Inner no Rodapé da Janela
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " PING ONLINE...");

            //Envia o comando de PING ON LINE, se o retorno for OK volta para o estado onde chamou o método
            int retorno = easyInner.PingOnLine(typInnersCadastrados[lngInnerAtual].Numero);
            if (retorno == easyInner.RET_COMANDO_OK) {
                typInnersCadastrados[lngInnerAtual].EstadoAtual = typInnersCadastrados[lngInnerAtual].EstadoSolicitacaoPingOnLine;
            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }
            typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = System.currentTimeMillis();
        } catch (Exception ex) {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
        }
    }

    /**
     * Se a conexão cair tenta conectar novamente Próximo Passo:
     * ESTADO_ENVIAR_CFG_OFFLINE
     */
    private void PASSO_ESTADO_RECONECTAR() {
        try {
            long IniConexao;

            long tempo = System.currentTimeMillis() - typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine;
            if (tempo < 10000) {
                return;
            }
            typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = (int) System.currentTimeMillis();

            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Reconectando...");
            Ret = Enumeradores.Limpar;

            IniConexao = System.currentTimeMillis();
            //Realiza loop enquanto o tempo fim for menor que o tempo atual, e o comando retornado diferente de OK.
            do {
                tempo = System.currentTimeMillis() - IniConexao;

                Ret = testarConexaoInner(typInnersCadastrados[lngInnerAtual].Numero);

                Thread.sleep(10l);

            } while (Ret != Enumeradores.RET_COMANDO_OK && tempo < 15000);

            //Testa o comando de envio de relógio para o Inner
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //Zera as variáveis de controle da maquina de estados.
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                //Recebendo Versao de firmware, passo obrigatorio
                DefineVersao();
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_CFG_OFFLINE;
            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando >= 3) {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando++;
            }

            typInnersCadastrados[lngInnerAtual].CountRepeatPingOnline = 0;
        } catch (Exception ex) {
            System.out.println("Passo Reconectar :  " + ex);
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    /**
     * Realiza a crição do array de equipamentos para serem controlados.
     *
     * @throws InterruptedException
     */
    private void iniciarMaquinaEstados() throws InterruptedException {

        //Catraca
        if ((jCboEquipamento.getSelectedIndex() != Enumeradores.Acionamento_Coletor) && (!(jOptDireita.isSelected()) && (!(jOptEsquerda.isSelected())))) {
            JOptionPane.showMessageDialog(null, "Favor informar o lado de instalação da catraca !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        jBtnIniciar.setEnabled(false);
        jBtnParar.setEnabled(true);
        jBtnLimpar.setEnabled(true);

        jLblStatus.setText("");
        jLblDados.setText("");
        jTxaVersao.setText("");
        jBtnIniciar.setEnabled(false);

        //******************************************************
        //MAIS DE UM INNER
        //Define a quantidade de Inners que o sistema terá..
        TotalInners = jTblInners.getRowCount();
        lngInnerAtual = 0;

        //Atribui o vetor com os números dos Inners, sempre de 1 a N
        for (int i = 0; i < TotalInners; i++) {

            typInnersCadastrados[i] = new Inner();
            typInnersCadastrados[i].Numero = (int) jTblInners.getValueAt(i, 0);
            typInnersCadastrados[i].QtdDigitos = (int) jTblInners.getValueAt(i, 1);
            typInnersCadastrados[i].Teclado = (boolean) jTblInners.getValueAt(i, 2);
            typInnersCadastrados[i].Lista = (boolean) jTblInners.getValueAt(i, 3);
            typInnersCadastrados[i].ListaBio = (boolean) jTblInners.getValueAt(i, 4);
            typInnersCadastrados[i].TipoLeitor = (int) jTblInners.getValueAt(i, 5);
            typInnersCadastrados[i].Identificacao = ((boolean) jTblInners.getValueAt(i, 6) ? 1 : 0);
            typInnersCadastrados[i].Verificacao = ((boolean) jTblInners.getValueAt(i, 7) ? 1 : 0);
            typInnersCadastrados[i].DoisLeitores = (boolean) jTblInners.getValueAt(i, 8);
            typInnersCadastrados[i].Catraca = (boolean) jTblInners.getValueAt(i, 9);
            typInnersCadastrados[i].Biometrico = (boolean) jTblInners.getValueAt(i, 10);
            typInnersCadastrados[i].CntDoEvents = 0;
            typInnersCadastrados[i].CountPingFail = 0;
            typInnersCadastrados[i].CountTentativasEnvioComando = 0;
            typInnersCadastrados[i].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
            typInnersCadastrados[i].TempoInicialPingOnLine = (int) System.currentTimeMillis();
            typInnersCadastrados[i].EstadoTeclado = Enumeradores.EstadosTeclado.TECLADO_EM_BRANCO;
        }

       
        //Fecha qualquer conexão que estivesse aberta..
        easyInner.FecharPortaComunicacao();
        //Define o tipo de conexão conforme o selecionado no combo (serial, TCP porta Variavel, TCP Porta Fixa..etc)
        easyInner.DefinirTipoConexao(jCboTipoConexao.getSelectedIndex());

        //Abre a porta de Comunicação com os Inners..
        Ret = easyInner.AbrirPortaComunicacao(Integer.parseInt(jSpnPorta.getValue().toString()));

        //Caso o retorno seja OK, abre a maquina de Estados..
        if (Ret == Enumeradores.RET_COMANDO_OK) {
            Parar = false;
            MaquinaEstados();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao tentar abrir a porta de comunicação.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            jBtnIniciar.setEnabled(true);
        }
    }

    /**
     * Monta as configurações necessária para o funcionamento do Inner. Esta
     * função é utilizada on-line ou off-line. modo = 0 off line/modo = 1 on
     * line
     *
     * @param modo
     */
    private void MontaConfiguracaoInner(int modo) {
        try {
            // ANTES de realizar a configuração precisa definir o Padrão do cartão
            if (jCboPadraoCartao.getSelectedIndex() == 1) {
                easyInner.DefinirPadraoCartao(Enumeradores.PADRAO_LIVRE);
            } else {
                easyInner.DefinirPadraoCartao(Enumeradores.PADRAO_TOPDATA);
            }

            //Define Modo de comunicação
            if (modo == Enumeradores.MODO_OFF_LINE) {
                //Configurações para Modo Offline.
                //Prepara o Inner para trabalhar no modo Off-Line, porém essa função
                //ainda não envia essa informação para o equipamento.
                easyInner.ConfigurarInnerOffLine();
            } else {
                //Configurações para Modo Online.
                //Prepara o Inner para trabalhar no modo On-Line, porém essa função
                //ainda não envia essa informação para o equipamento.
                easyInner.ConfigurarInnerOnLine();
            }

            //Verificar
            //Acionamentos 1 e 2
            //Configura como irá funcionar o acionamento(rele) 1 e 2 do Inner, e por
            //quanto tempo ele será acionado.
            switch (jCboEquipamento.getSelectedIndex()) {
                //Coletor
                case Enumeradores.Acionamento_Coletor:
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 5);
                    easyInner.ConfigurarAcionamento2(Enumeradores.ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 5);
                    break;

                //Catraca
                case Enumeradores.Acionamento_Catraca_Entrada_E_Saida:
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 5);
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Entrada:
                    if(jOptEsquerda.isSelected())
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_SAIDA, 5);    
                    } 
                    else
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA, 5);
                    }
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Saida:
                    if(jOptEsquerda.isSelected())
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA, 5);    
                    } 
                    else
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_SAIDA, 5);
                    }
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break; 
                      

                case Enumeradores.Acionamento_Catraca_Urna:
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 5);
                    easyInner.ConfigurarAcionamento2(Enumeradores.ACIONA_REGISTRO_SAIDA, 5);
                    break;

                case Enumeradores.Acionamento_Catraca_Saida_Liberada:
                    //Se Esquerda Selecionado - Inverte código
                    if(jOptEsquerda.isSelected()&&(!typInnersCadastrados[lngInnerAtual].InnerNetAcesso))
                    {
                            easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_ENTRADA_LIBERADA, 5);
                    }
                    else
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_SAIDA_LIBERADA, 5);
                    }
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Entrada_Liberada:
                    //Se Esquerda Selecionado - Inverte código
                    if(jOptEsquerda.isSelected()&&(!typInnersCadastrados[lngInnerAtual].InnerNetAcesso))
                    {
                            easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_SAIDA_LIBERADA, 5);
                    }
                    else
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_ENTRADA_LIBERADA, 5);
                    }
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Liberada_2_Sentidos:
                    easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_LIBERADA_DOIS_SENTIDOS, 5);
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Sentido_Giro:
                    easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_LIBERADA_DOIS_SENTIDOS_MARCACAO_REGISTRO, 5);
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;
            }

            //Configurar tipo do leitor
            switch (typInnersCadastrados[lngInnerAtual].TipoLeitor) {
                case Enumeradores.CODIGO_DE_BARRAS:
                    easyInner.ConfigurarTipoLeitor(Enumeradores.CODIGO_DE_BARRAS);
                    break;
                case Enumeradores.MAGNETICO:
                    easyInner.ConfigurarTipoLeitor(Enumeradores.MAGNETICO);
                    break;
                case Enumeradores.PROXIMIDADE_ABATRACK2:
                    easyInner.ConfigurarTipoLeitor(Enumeradores.PROXIMIDADE_ABATRACK2);
                    break;
                case Enumeradores.WIEGAND:
                    easyInner.ConfigurarTipoLeitor(Enumeradores.WIEGAND);
                    break;
                case Enumeradores.PROXIMIDADE_SMART_CARD:
                    easyInner.ConfigurarTipoLeitor(Enumeradores.PROXIMIDADE_SMART_CARD);
                    break;
                case Enumeradores.CODIGO_BARRAS_SERIAL:
                    easyInner.ConfigurarTipoLeitor(Enumeradores.CODIGO_BARRAS_SERIAL);
                    break;
                case Enumeradores.WIEGAND_FC_SEM_ZERO:
                    easyInner.ConfigurarTipoLeitor(Enumeradores.WIEGAND_FC_SEM_ZERO);
                    break;
            }

            easyInner.DefinirQuantidadeDigitosCartao(typInnersCadastrados[lngInnerAtual].QtdDigitos);

            //Habilitar teclado
            easyInner.HabilitarTeclado((typInnersCadastrados[lngInnerAtual].Teclado ? Enumeradores.Opcao_SIM : Enumeradores.Opcao_NAO), 0);

            //Define os valores para configurar os leitores de acordo com o tipo de inner
            DefineValoresParaConfigurarLeitores();
            easyInner.ConfigurarLeitor1(typInnersCadastrados[lngInnerAtual].ValorLeitor1);
            easyInner.ConfigurarLeitor2(typInnersCadastrados[lngInnerAtual].ValorLeitor2);

            //Box = Configura equipamentos com dois leitores
            if (typInnersCadastrados[lngInnerAtual].DoisLeitores) {
                // exibe mensagens do segundo leitor
                easyInner.ConfigurarWiegandDoisLeitores(1, Enumeradores.Opcao_SIM);
            }

            // Registra acesso negado
            easyInner.RegistrarAcessoNegado(1);

            //Catraca
            //Define qual será o tipo do registro realizado pelo Inner ao aproximar um
            //cartão do tipo proximidade no leitor do Inner, sem que o usuário tenha
            //pressionado a tecla entrada, saída ou função.
            if ((jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Entrada_E_Saida) || (jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Liberada_2_Sentidos) || (jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Sentido_Giro)) {
                easyInner.DefinirFuncaoDefaultLeitoresProximidade(12); // 12 – Libera a catraca nos dois sentidos e registra o bilhete conforme o sentido giro.
            } else {
                if ((jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Entrada) || (jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Saida_Liberada)) {
                    if (jOptDireita.isSelected()) {
                        easyInner.DefinirFuncaoDefaultLeitoresProximidade(10);  // 10 – Registrar sempre como entrada.
                    } else {
                        easyInner.DefinirFuncaoDefaultLeitoresProximidade(11);  // 11 – Registrar sempre como saída.
                    }
                } else {
                    if (jOptDireita.isSelected()) {
                        easyInner.DefinirFuncaoDefaultLeitoresProximidade(11);  // 11 – Registrar sempre como saída.
                    } else {
                        easyInner.DefinirFuncaoDefaultLeitoresProximidade(10);  // 10 – Registrar sempre como entrada.
                    }
                }
            }

            //Configura o tipo de registro que será associado a uma marcação
            if (typInnersCadastrados[lngInnerAtual].Biometrico) {
                easyInner.DefinirFuncaoDefaultSensorBiometria(10);
            } else {
                easyInner.DefinirFuncaoDefaultSensorBiometria(0);
            }

            //Configura para receber o horario dos dados qdo Online.
            if (typInnersCadastrados[lngInnerAtual].QtdDigitos <= 14) {
                easyInner.ReceberDataHoraDadosOnLine(Enumeradores.Opcao_SIM);
            }

            // Caso desejar configurar o Inner para ler cartoes que possam 
            // variar de 1 dígito até 16 dígitos
            // utilizar a funcao InserirQuantidadeDigitoVariavel
            // easyInner.InserirQuantidadeDigitoVariavel(8);
            // easyInner.InserirQuantidadeDigitoVariavel(10);
            // easyInner.InserirQuantidadeDigitoVariavel(14);
            //easyInner.ConfigurarBioVariavel(1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
    }

    /**
     * FUNCIONAMENTO DA MÁQUINA DE ESTADOS MÉTODO RESPONSÁVEL EM EXECUTAR OS
     * PROCEDIMENTOS DO MODO ONLINE A Máquina de Estados nada mais é do que uma
     * rotina que fica em loop testando uma variável que chamamos de Estado.
     * Dependendo do estado atual, executamos alguns procedimentos e em seguida
     * alteramos o estado que será verificado pela máquina de estados novamente
     * no próximo passo do loop.
     *
     * @throws InterruptedException
     */
    private void MaquinaEstados() throws InterruptedException {

        //Enquanto Parar = false prosseguir a maquina...
        while (!Parar) {
            //Verifica o Estado do Inner Atual..
            switch (typInnersCadastrados[lngInnerAtual].EstadoAtual) {
                case ESTADO_CONECTAR:
                    PASSO_ESTADO_CONECTAR();
                    break;

                case ESTADO_ENVIAR_CFG_OFFLINE:
                    PASSO_ESTADO_ENVIAR_CFG_OFFLINE();
                    break;

                case ESTADO_COLETAR_BILHETES:
                    PASSO_ESTADO_COLETAR_BILHETES();
                    break;

                case ESTADO_ENVIAR_CFG_ONLINE:
                    PASSO_ESTADO_ENVIAR_CFG_ONLINE();
                    break;

                case ESTADO_ENVIAR_DATA_HORA:
                    PASSO_ESTADO_ENVIAR_DATA_HORA();
                    break;

                case ESTADO_ENVIAR_MSG_PADRAO:
                    PASSO_ENVIAR_MENSAGEM_PADRAO();
                    break;

                case ESTADO_CONFIGURAR_ENTRADAS_ONLINE:
                    PASSO_ESTADO_CONFIGURAR_ENTRADAS_ONLINE();
                    break;

                case ESTADO_POLLING:
                    PASSO_ESTADO_POLLING();
                    break;

                case ESTADO_LIBERAR_CATRACA:
                    PASSO_LIBERA_GIRO_CATRACA();
                    break;

                case ESTADO_MONITORA_GIRO_CATRACA:
                    PASSO_MONITORA_GIRO_CATRACA();
                    break;

                case PING_ONLINE:
                    PASSO_ESTADO_ENVIA_PING_ONLINE();
                    break;

                case ESTADO_RECONECTAR:
                    PASSO_ESTADO_RECONECTAR();
                    break;

                case AGUARDA_TEMPO_MENSAGEM:
                    PASSO_AGUARDA_TEMPO_MENSAGEM();
                    break;

                case ESTADO_DEFINICAO_TECLADO:
                    PASSO_ESTADO_DEFINICAO_TECLADO();
                    break;

                case ESTADO_AGUARDA_DEFINICAO_TECLADO:
                    PASSO_ESTADO_AGUARDA_DEFINICAO_TECLADO();
                    break;

                case ESTADO_ENVIA_MSG_URNA:
                    PASSO_ESTADO_ENVIA_MSG_URNA();
                    break;

                case ESTADO_MONITORA_URNA:
                    PASSO_ESTADO_MONITORA_URNA();
                    break;

                case ACIONAR_RELE:
                    ACIONAR_RELE();
                    break;

                case ESTADO_ENVIAR_CONFIGMUD_ONLINE_OFFLINE:
                    PASSO_ESTADO_ENVIAR_CONFIGMUD_ONLINE_OFFLINE();
                    break;

                case ESTADO_ENVIAR_MENSAGEM:
                    PASSO_ESTADO_ENVIAR_MSG_OFFLINE();
                    break;

                case ESTADO_ENVIAR_HORARIOS:
                    PASSO_ESTADO_ENVIAR_HORARIOS();
                    break;

                case ESTADO_ENVIAR_MENSAGEM_ACESSO_NEGADO:
                    PASSO_ENVIAR_MENSAGEM_ACESSO_NEGADO();
                    break;

                case ESTADO_ENVIAR_USUARIOS_LISTAS:
                    PASSO_ESTADO_ENVIAR_USUARIOS_LISTAS();
                    break;
                    
                case ESTADO_VALIDA_URNA_CHEIA:
                    PASSO_ESTADO_VALIDA_URNA_CHEIA();
                    break;
                
                case ESTADO_ENVIAR_MSG_URNA_CHEIA:
                    PASSO_ESTADO_ENVIAR_MSG_URNA_CHEIA();
                    
                case ESTADO_VALIDAR_ACESSO:
                    PASSO_ESTADO_VALIDAR_ACESSO();
                    
            }

            if (typInnersCadastrados[lngInnerAtual].CntDoEvents++ > 10) {
                typInnersCadastrados[lngInnerAtual].CntDoEvents = 0;

                Thread.sleep(1000l);

            }

            //INCREMENTA A VARIÁVEL QUE FAZ A CONTAGEM DE INNERS..
            lngInnerAtual = lngInnerAtual + 1;

            //CASO O VALOR INCREMENTAL FOR MAIOR QUE A QUANTIDADE TOTAL, REATRIBUI 0
            //PARA VARIÁVEL..
            if (lngInnerAtual > TotalInners - 1) {
                lngInnerAtual = 0;
            }
        }

        //Fecha a porta de Comunicação quando sai da maquina de estados..
        easyInner.FecharPortaComunicacao();
    }

    /**
     * CONFIGURAÇÃO LEITORES De acordo com o lado da catraca, coletor ou se é
     * dois leitores
     */
    private void DefineValoresParaConfigurarLeitores() {

        //Configuração Catraca Esquerda ou Direita
        //define os valores para configurar os leitores de acordo com o tipo de inner
        if (typInnersCadastrados[lngInnerAtual].DoisLeitores) {
            if (jOptDireita.isSelected()) {
                //Direita Selecionado
                typInnersCadastrados[lngInnerAtual].ValorLeitor1 = Enumeradores.SOMENTE_ENTRADA;
                typInnersCadastrados[lngInnerAtual].ValorLeitor2 = Enumeradores.SOMENTE_SAIDA;
            } else {
                //Esquerda Selecionado
                typInnersCadastrados[lngInnerAtual].ValorLeitor1 = Enumeradores.SOMENTE_SAIDA;
                typInnersCadastrados[lngInnerAtual].ValorLeitor2 = Enumeradores.SOMENTE_ENTRADA;
            }
        } else {
            if (jOptDireita.isSelected()) {
                //Direita Selecionado   
                typInnersCadastrados[lngInnerAtual].ValorLeitor1 = Enumeradores.ENTRADA_E_SAIDA;
            } else {
                //Esquerda Selecionado
                typInnersCadastrados[lngInnerAtual].ValorLeitor1 = Enumeradores.ENTRADA_E_SAIDA_INVERTIDAS;
            }

            typInnersCadastrados[lngInnerAtual].ValorLeitor2 = Enumeradores.DESATIVADO;

        }
    }

    /**
     * Define Mudanças OnLine Função que configura BIT a BIT, Ver no manual
     * Anexo III
     *
     * @param InnerAtual
     * @return
     */
    private static int ConfiguraEntradasMudancaOnLine(Inner InnerAtual) {
        String Configuracao;

        //Habilita Teclado
        Configuracao = (typInnersCadastrados[lngInnerAtual].Teclado ? "1" : "0");

        if (!typInnersCadastrados[lngInnerAtual].Biometrico) {
            //Código de Barras e Proximidade

            //Dois leitores
            if (typInnersCadastrados[lngInnerAtual].DoisLeitores) {
                Configuracao = "010" + //Leitor 2 só saida
                        "001" + //Leitor 1 só entrada
                        Configuracao;
            } else { //Apenas um leitores
                Configuracao = "000" + //Leitor 2 Desativado
                        "011" + //Leitor 1 configurado para Entrada e Saída
                        Configuracao;
            }

            Configuracao = "1" + // Habilitado
                    Configuracao;

            /*
             --------------------------------------------------------------------------------------------------
             |       7        |     6      |   5    |   4    |   3    |    2    |      1     |        0       |
             --------------------------------------------------------------------------------------------------
             | Seta/Reseta    |  Bit 2     |  Bit 1 |  Bit 0 | Bit 2  |  Bit 1  |   Bit 0    |  Teclado       |
             |   config.      | Leitor 2   |        |        |        |         |            |                |
             |   bit-a-bit    |            |        |        |        |         |            |                |
             --------------------------------------------------------------------------------------------------
             | 1 – Habilita   | 000 – Desativa leitor        |  000 - Desativa leitor        | 1 – Habilita   |
             | 0 – Desabilita | 001 - Leitor só entrada      |  001 - Leitor só entrada      | 0 – Desabilita |
             |                | 010 - Leitor só saída        |  010 - Leitor só saída        |                |
             |                | 011 - Leitor Entrada e saída |  011 - Leitor Entrada e saída |                |
             |                | 100 - Leitor Entrada e Saída |  100 - Leitor Entrada e       |                |
             |                |   Invertido                  |   Saída Invertido             |                |
             --------------------------------------------------------------------------------------------------
             */
        } else { //Com Biometria

            Configuracao = "0" + //Bit Fixo
                    "1" + //Habilitado
                    typInnersCadastrados[lngInnerAtual].Identificacao + //Identificação
                    typInnersCadastrados[lngInnerAtual].Verificacao + //Verificação
                    "0" + //Bit fixo
                    (typInnersCadastrados[lngInnerAtual].DoisLeitores ? "11" : "10") + // 11 -> habilita leitor 1 e 2, 10 -> habilita apenas leitor 1
                    Configuracao;

            /*
             ------------------------------------------------------------------------------------------------------------------------
             |    7     |       6       |       5       |       4       |      3       |       2      |      1       |      0       |
             ------------------------------------------------------------------------------------------------------------------------
             | Bit fixo | Seta/Reseta   | Identificação |  Verificação  |   Bit fixo   |   Leitor 1   | Leitor 2     |  Teclado     |
             |   '0'    |    config.    |      Bio      |      Bio      |    Config    |              |              |              |
             |          | bit-a-bit bio |               |               |      L2      |              |              |              |
             |          |               |               |               |     '0'      |              |              |              |
             ------------------------------------------------------------------------------------------------------------------------
             |    0     |  1-Habilita   | 1-Habilita    | 1-Habilita    | 1-Habilita   | 1-Habilita   | 1-Habilita   | 1-Habilita   |
             |          |  0-Desabilita | 0-Desabilita  | 0-Desabilita  | 0-Desabilita | 0-Desabilita | 0-Desabilita | 0-Desabilita |
             ------------------------------------------------------------------------------------------------------------------------
             */
        }

        //Converte Binário para Decimal
        return BinarioParaDecimal(Configuracao);
    }

    /**
     * Realiza a conversão Binário para Decimal
     *
     * @param valorBinario
     * @return
     */
    private static int BinarioParaDecimal(String valorBinario) {
        int length_bin = 0, aux = 0, retorno = 0, i;
        length_bin = valorBinario.length();

        for (i = 0; i < length_bin; i++) {
            aux = Integer.parseInt(valorBinario.substring(i, i + 1));
            retorno += aux * (int) Math.pow(2, (length_bin - i)) / 2;
        }
        return (retorno);
    }

    /**
     * Esta rotina é responsável por identificar a versão do inner e modelo.
     *
     * @throws InterruptedException
     */
    private void DefineVersao() throws InterruptedException {
        int Versao[] = new int[30];
        long Variacao = 0;
        int Modelo[] = new int[3];

        //Solicita a versão do firmware do Inner e dados como o Idioma, se é
        //uma versão especial.
        Ret = easyInner.ReceberVersaoFirmware(typInnersCadastrados[lngInnerAtual].Numero, Versao);

        typInnersCadastrados[lngInnerAtual].InnerAcessoBio = Versao[6];
        //Se selecionado Biometria, valida se o equipamento é compatível
        if (jChkBiometrico.isSelected()) {
            if ((((Versao[0] != 6) && (Versao[0] != 14)) || ((Versao[0] == 14) && (typInnersCadastrados[lngInnerAtual].InnerAcessoBio == 0))) && jChkBiometrico.isSelected()) {
                JOptionPane.showMessageDialog(null, "Equipamento " + String.valueOf(typInnersCadastrados[lngInnerAtual].Numero) + " não compatível com Biometria.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (Ret == Enumeradores.RET_COMANDO_OK) {
            //Define a linha do Inner
            switch (Versao[0]) {
                case 1:
                    typInnersCadastrados[lngInnerAtual].LinhaInner = "Inner Plus";
                    break;

                case 2:
                    typInnersCadastrados[lngInnerAtual].LinhaInner = "Inner Disk";
                    break;

                case 3:
                    typInnersCadastrados[lngInnerAtual].LinhaInner = "Inner Verid";
                    break;

                case 6:
                    typInnersCadastrados[lngInnerAtual].LinhaInner = "Inner Bio";
                    break;

                case 7:
                    typInnersCadastrados[lngInnerAtual].LinhaInner = "Inner NET";
                    break;

                case 14:
                    typInnersCadastrados[lngInnerAtual].LinhaInner = "Inner Acesso";
                    typInnersCadastrados[lngInnerAtual].InnerNetAcesso = true;
                    break;
            }

            typInnersCadastrados[lngInnerAtual].VariacaoInner = Variacao;
            typInnersCadastrados[lngInnerAtual].VersaoInner = Integer.toString(Versao[3]) + '.' + Integer.toString(Versao[4]) + '.' + Integer.toString(Versao[5]);

            //Se for biometria
            if ((Versao[0] == 6) || (Versao[0] == 14 && typInnersCadastrados[lngInnerAtual].InnerAcessoBio == 1)) {

                //Solicita o modelo do Inner bio.
                Ret = easyInner.SolicitarModeloBio(typInnersCadastrados[lngInnerAtual].Numero);

                do {
                    //Retorna o resultado do comando SolicitarModeloBio, o modelo
                    //do Inner Bio é retornado por referência no parâmetro da função.
                    Ret = easyInner.ReceberModeloBio(typInnersCadastrados[lngInnerAtual].Numero, 0, Modelo);
                    Thread.sleep(100);
                } while (Ret == 128);

                //Define o modelo do Inner Bio
                switch (Modelo[0]) {
                    case 1:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: Light 100 usuários FIM10";
                        break;
                    case 4:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: 1000/4000 usuários FIM01";
                        break;
                    case 51:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: 1000/4000 usuários FIM2030";
                        break;
                    case 52:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: 1000/4000 usuários FIM2040";
                        break;
                    case 48:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: Light 100 usuários FIM3030";
                        break;
                    case 64:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: Light 100 usuários FIM3040";
                        break;
                    case 80:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: FIM5060";
                        break;
                    case 82:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: FIM5260";
                        break;
                    case 83:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: FIM5360";
                        break;
                    case 96:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: FIM6060";
                        break;
                    case 255:
                        typInnersCadastrados[lngInnerAtual].ModeloBioInner = "Modelo do bio: Desconhecido";
                        break;
                }

                //Solicita a versão do Inner bio.
                Ret = easyInner.SolicitarVersaoBio(typInnersCadastrados[lngInnerAtual].Numero);

                do {
                    //Retorna o resultado do comando SolicitarVersaoBio, a versão
                    //do Inner Bio é retornado por referência nos parâmetros da
                    //função.
                    Thread.sleep(100);
                    Ret = easyInner.ReceberVersaoBio(typInnersCadastrados[lngInnerAtual].Numero, 0, Versao);
                } while (Ret == 128);
                typInnersCadastrados[lngInnerAtual].VersaoBio = Integer.toString(Versao[0]) + "." + Integer.toString(Versao[1]);
                jTxaVersao.append(typInnersCadastrados[lngInnerAtual].LinhaInner + " - Versão: " + typInnersCadastrados[lngInnerAtual].VersaoInner + " ");
                jTxaVersao.append(typInnersCadastrados[lngInnerAtual].ModeloBioInner + " -> " + typInnersCadastrados[lngInnerAtual].VersaoBio + "\r\n");
            }

        }
    }

    /**
     * MONTAR HORARIOS Insere no buffer da dll um horário de acesso. O Inner
     * possui uma tabela de 100 horários de acesso, para cada horário é possível
     * definir 4 faixas de acesso para cada dia da semana. Tabela de horários
     * numero 1
     *
     * @throws Exception
     */
    private void MontarHorarios() throws Exception {

        //Insere no buffer da DLL horario de acesso    
        byte bTime;
        byte bDay;
        byte bRange;
        byte bHour;
        byte bMin;
        
        List<Horarios> listaHorarios = consultarHorarios();
        
        for(int i = 0;listaHorarios.size()>i;i++)
        {
            bTime  = Byte.parseByte(listaHorarios.get(i).getHorario());
            bDay   = (byte) listaHorarios.get(i).getDia();
            bRange = (byte) listaHorarios.get(i).getFaixa();
            bHour  = (byte) listaHorarios.get(i).getHora();
            bMin   = (byte) listaHorarios.get(i).getMinuto();
            
            
            easyInner.InserirHorarioAcesso(bTime, bDay, bRange, bHour, bMin);
        }
        
        easyInner.EnviarListaAcesso(Integer.parseInt(jSpnNumInner.getValue().toString()));
        
    }

    /**
     * MONTAR LISTA TOPDATA //Monta o buffer para enviar a lista nos inners da
     * linha Inner, cartão padrão Topdata
     *
     */
    private void MontarListaTopdata() {
        easyInner.DefinirPadraoCartao(0);

        easyInner.DefinirQuantidadeDigitosCartao(14);

        for (int i = 0; i < 5; i++) {
            easyInner.InserirUsuarioListaAcesso(Integer.toString(i), 101);
        }
        easyInner.EnviarListaAcesso(Integer.parseInt(jSpnNumInner.getValue().toString()));
    }

    /**
     * MONTAR LISTA LIVRE Monta o buffer para enviar a lista nos inners da linha
     * Inner, cartão padrão livre 14 dígitos
     *
     * @throws Exception
     */
    private void MontarListaLivre() throws Exception {
        easyInner.DefinirPadraoCartao(1);

        easyInner.DefinirQuantidadeDigitosCartao(typInnersCadastrados[lngInnerAtual].QtdDigitos);
        
        try
        {
            List<Usuarios> listaCartao  = DAOUsuarios.consultarUsuarios(0);
            
            /*Finding if the card is included at the listOffLine, it can be 
            Online list too*/
            for (int i=0 ;listaCartao.size() >i; i++) 
            {                
                easyInner.InserirUsuarioListaAcesso(listaCartao.get(i).getUsuario(), 101);
            }
                    //insere usuário na lista de acesso, sem zero a esquerda
            easyInner.EnviarListaAcesso(Integer.parseInt(jSpnNumInner.getValue().toString()));
        }
        catch(IOException | SQLException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Monta lista de usuários que não possuem digitais cadastradas, caso a
     * Biometria esteja habilitada para verificação não sera solicitado.
     *
     * @throws Exception
     */
    private void MontarBufferListaSemDigital() throws Exception {
        List<UsuarioSemDigital> USersSD = UsersBio.ConsultarUsuarioSemDigital();
        for (UsuarioSemDigital s : USersSD) {
            if (InnerAtual.InnerNetAcesso)
            {
                easyInner.IncluirUsuarioSemDigitalBioInnerAcesso(s.getCartao());
            }
            else
            {
                easyInner.IncluirUsuarioSemDigitalBio(s.getCartao());
            }
        }
    }
     
       
    /**
     * É onde funciona todo o processo do modo online Passagem de cartão,
     * catraca, urna, mensagens...
     *
     */
    private void PASSO_ESTADO_POLLING() 
    {
        try 
        {
            StringBuffer Cartao = new StringBuffer();
            //Exibe estado do Inner no Rodapé da Janela
            jLblStatus.setText("Inner " + typInnersCadastrados[lngInnerAtual].Numero + " Estado de Polling...");
            Cartao.delete(0, Cartao.length());
            Ret = -1;
            
            int[] iArrBCartaoRb = new int[8]; /*it receives the card data*/
            //Envia o Comando de Coleta de Bilhetes..
            Ret = easyInner.ReceberDadosOnLine(typInnersCadastrados[lngInnerAtual].Numero, iArrBCartaoRb, Cartao);

            //Atribui Temporizador
            typInnersCadastrados[lngInnerAtual].Temporizador = (int) System.currentTimeMillis();

            if (Ret == Enumeradores.RET_COMANDO_OK) 
            {
                if (iArrBCartaoRb[0] == Enumeradores.FIM_TEMPO_ACIONAMENTO
                        || iArrBCartaoRb[0] == Enumeradores.GIRO_DA_CATRACA_TOPDATA
                        || iArrBCartaoRb[0] == Enumeradores.TECLA_FUNCAO
                        || iArrBCartaoRb[0] == Enumeradores.TECLA_ANULA
                        || ((Cartao.length() == 0)
                        && !(typInnersCadastrados[lngInnerAtual].EstadoTeclado == Enumeradores.EstadosTeclado.AGUARDANDO_TECLADO))) 
                {
                    typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_PADRAO;
                    return;
                }

                
                /*******************************************************/
                //Assigning the card data into the current Inner
                typInnersCadastrados[lngInnerAtual].BilheteInner.Origem        = iArrBCartaoRb[0];
                typInnersCadastrados[lngInnerAtual].BilheteInner.Complemento   = iArrBCartaoRb[1];
                typInnersCadastrados[lngInnerAtual].BilheteInner.Dia           = iArrBCartaoRb[2];
                typInnersCadastrados[lngInnerAtual].BilheteInner.Mes           = iArrBCartaoRb[3];
                typInnersCadastrados[lngInnerAtual].BilheteInner.Ano           = iArrBCartaoRb[4];
                typInnersCadastrados[lngInnerAtual].BilheteInner.Hora          = iArrBCartaoRb[5];
                typInnersCadastrados[lngInnerAtual].BilheteInner.Minuto        = iArrBCartaoRb[6];
                typInnersCadastrados[lngInnerAtual].BilheteInner.Segundo       = iArrBCartaoRb[7];
                
                /*Cleaning the Strinbuilder variable and assigning a new value*/
                typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao.setLength(0);
                typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao = new StringBuilder(Cartao.toString());
                
                
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_VALIDAR_ACESSO;
            
            }    
            else 
            {
                long temp = System.currentTimeMillis() - typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine;
                //Se passar 3 segundos sem receber nada, passa para o estado enviar ping on line, para manter o equipamento em on line.
                if ((int) temp > 3000) {
                    typInnersCadastrados[lngInnerAtual].EstadoSolicitacaoPingOnLine = typInnersCadastrados[lngInnerAtual].EstadoAtual;
                    typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                    typInnersCadastrados[lngInnerAtual].TempoInicialPingOnLine = (int) System.currentTimeMillis();
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.PING_ONLINE;
                }
            }

        } catch (Exception ex) {
            System.err.println(ex);
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }
    
    
   private void PASSO_ESTADO_VALIDAR_ACESSO(){
        
        String sBilheteRecebido = typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao.toString();
        int tam;
        
        if (typInnersCadastrados[lngInnerAtual].QtdDigitos > sBilheteRecebido.length())
        {      
            tam = sBilheteRecebido.length();
        }
        else
        {
            tam = typInnersCadastrados[lngInnerAtual].QtdDigitos;
        }
        
        
        String sNumCartao = "";
        
        //Se o cartão padrão for topdata, configura os dígitos do cartão como padrão topdata
        if (jCboPadraoCartao.getSelectedIndex() == 0) 
        {
            //Padrão Topdata --> Cartão Topdata deve ter sempre 14 dígitos.
                    //5 dígitos5
            sNumCartao = Long.toString(Long.parseLong(sBilheteRecebido.substring(0,tam)));
            sNumCartao = sNumCartao.substring(13, 14) + "" + sNumCartao.substring(4, 8);
            
            //Gravando no Inner o Numero do Cartao formatado
            StringBuilder sbAux = new StringBuilder(sNumCartao);
            typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao.delete(0,typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao.length());
            typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao = new StringBuilder(sNumCartao);

        } 
        else 
        {
            /*removing the leading zeros from the received card and extracting the buffer waste, 
            it can be executed as the following form
            typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao.delete( 0, typInnersCadastrados[lngInnerAtual].BilheteInnner.Cartao.length());
            typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao = new StringBuilder( Long.toString(Long.parseLong(sBilheteRecebido.substring(0,tam))));
            */
            sNumCartao = Long.toString(Long.parseLong(sBilheteRecebido.substring(0,tam)));
                        
            //Gravando no Inner o Numero do Cartao formatado
            typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao.delete( 0, typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao.length());
            typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao = new StringBuilder(sNumCartao);
        }
        
        String sBilheteDisplay;
        
        sBilheteDisplay = ""; 
        
        sBilheteDisplay = "Marcações Online. Inner: " + typInnersCadastrados[lngInnerAtual].Numero + " Complemento:"
                        + String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Complemento);

        //Se Quantidade de dígitos informado for maior que 14 não deve mostrar data e hora
        if (typInnersCadastrados[lngInnerAtual].QtdDigitos <= 14) 
        {
            sBilheteDisplay = sBilheteDisplay
                            + " Data:"
                            + (String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Dia).length() == 1 ? "0" + String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Dia) : String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Dia)) + "/"
                            + (String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Mes).length() == 1 ? "0" + String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Mes) : String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Mes)) + "/"
                            + String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Ano) + " Hora:"
                            + (String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Hora).length() == 1 ? "0" + String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Hora) : String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Hora)) + ":"
                            + (String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Minuto).length() == 1 ? "0" + String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Minuto) : String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Minuto)) + ":"
                            + (String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Segundo).length() == 1 ? "0" + String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Segundo) : String.valueOf(typInnersCadastrados[lngInnerAtual].BilheteInner.Segundo)) + " "
                            + " Cartão: " + typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao + "\n";
        } 
        else 
        {
            sBilheteDisplay = sBilheteDisplay + " Cartão: " + typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao + "\r\n";
        }
                
                
        //Adiciona bilhete coletado na Lista
        jTxaBilhetes.append(sBilheteDisplay);
        //typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_DEFINE_PROCESSO;
        //E Urna ou entrada e saída ou liberada 2 sentidos ou sentido giro
        //E cartão = proximidade        
        if (!LiberaAcesso(typInnersCadastrados[lngInnerAtual].BilheteInner.Cartao.toString())) 
        {
            typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MENSAGEM_ACESSO_NEGADO;
        } //Se 1 leitor
        //E Urna ou entrada e saída ou liberada 2 sentidos ou sentido giro
                //E cartão = proximidade
        else if (((jChkDoisLeitores.isSelected() == false)
                && ((jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Urna)
                || (jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Entrada_E_Saida)
                || (jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Liberada_2_Sentidos)
                || (jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Sentido_Giro))
                && ((jCboTipoLeitor.getSelectedIndex() == 2) || (jCboTipoLeitor.getSelectedIndex() == 3)
                || (jCboTipoLeitor.getSelectedIndex() == 4)))) {
            if (typInnersCadastrados[lngInnerAtual].EstadoTeclado == Enumeradores.EstadosTeclado.TECLADO_EM_BRANCO) {
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_DEFINICAO_TECLADO;
            }

             //Se estamos trabalhando com Urna e 1 leitor
            if ((typInnersCadastrados[lngInnerAtual].Catraca) && (jCboEquipamento.getSelectedIndex() == (int)Enumeradores.Acionamento_Catraca_Urna)) {
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIA_MSG_URNA;
            }
        } 
        else 
        {
            if (typInnersCadastrados[lngInnerAtual].Catraca) {
                if (jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Entrada) 
                {
                    habilitaLadoCatraca("Entrada"); 
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                } 
                else if (jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Saida) 
                {
                    habilitaLadoCatraca("Saida");
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                }
                
                //Acionamento saida liberada
                else if(jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Saida_Liberada)
                {
                    habilitaLadoCatraca("Entrada");
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                }    
                //Acionamento Etranda Liberada
                else if(jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Entrada_Liberada)
                {
                    habilitaLadoCatraca("Saida");
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                }
                
                //Se Urna e 2 leitores
                else if (jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Urna && typInnersCadastrados[lngInnerAtual].BilheteInner.Origem == Enumeradores.VIA_LEITOR2) 
                {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_VALIDA_URNA_CHEIA;
                } 
                else 
                {
                    typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                }
            } 
            else 
            {
                //Aciona Bip Curto..
                easyInner.AcionarBipCurto(typInnersCadastrados[lngInnerAtual].Numero);
                //Desliga Led Verde
                easyInner.LigarBackLite(typInnersCadastrados[lngInnerAtual].Numero);
                typInnersCadastrados[lngInnerAtual].TempoInicialMensagem = (int) System.currentTimeMillis();
                typInnersCadastrados[lngInnerAtual].CountTentativasEnvioComando = 0;
                easyInner.EnviarFormasEntradasOnLine(0, 0, 0, 0, 0, 0);
                easyInner.EnviarMensagemPadraoOnLine(typInnersCadastrados[lngInnerAtual].Numero, 0, "Acesso Liberado!");
                easyInner.AcionarRele1(typInnersCadastrados[lngInnerAtual].Numero);
                typInnersCadastrados[lngInnerAtual].EstadoAtual = Enumeradores.EstadosInner.AGUARDA_TEMPO_MENSAGEM;
            }
        }            
    }

   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel imgCatraca;
    private javax.swing.JButton jBtnAdcionarInner;
    private javax.swing.JButton jBtnEntrada;
    private javax.swing.JButton jBtnIniciar;
    private javax.swing.JButton jBtnLimpar;
    private javax.swing.JButton jBtnParar;
    private javax.swing.JButton jBtnRemoverInner;
    private javax.swing.JButton jBtnSaida;
    private javax.swing.JComboBox jCboEquipamento;
    private javax.swing.JComboBox jCboPadraoCartao;
    private javax.swing.JComboBox jCboTipoConexao;
    private javax.swing.JComboBox jCboTipoLeitor;
    private javax.swing.JCheckBox jChkBiometrico;
    private javax.swing.JCheckBox jChkCartaoMaster;
    private javax.swing.JCheckBox jChkDoisLeitores;
    private javax.swing.JCheckBox jChkHorarios;
    private javax.swing.JCheckBox jChkIdentificacao;
    private javax.swing.JCheckBox jChkLista;
    private javax.swing.JCheckBox jChkListaBio;
    private javax.swing.JCheckBox jChkTeclado;
    private javax.swing.JCheckBox jChkVerificacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLblDados;
    private javax.swing.JLabel jLblStatus;
    private javax.swing.JRadioButton jOptDireita;
    private javax.swing.JRadioButton jOptEsquerda;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpnNumInner;
    private javax.swing.JSpinner jSpnPorta;
    private javax.swing.JSpinner jSpnQtdDigitos;
    private javax.swing.JTable jTblInners;
    private javax.swing.JTextArea jTxaBilhetes;
    private javax.swing.JTextArea jTxaVersao;
    private javax.swing.JTextField jTxtCartaoMaster;
    private javax.swing.JLabel lblCatraca;
    private javax.swing.JLabel lblDadosRec;
    private javax.swing.JLabel lblInners;
    private javax.swing.JLabel lblNumDig;
    private javax.swing.JLabel lblPorta;
    private javax.swing.JLabel lblStatuss;
    private javax.swing.JLabel lblTipoEquipamento;
    // End of variables declaration//GEN-END:variables

    private void InserirHorarioAcesso(byte bTime, byte bDay, byte bRange, byte bHour, byte bMin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


