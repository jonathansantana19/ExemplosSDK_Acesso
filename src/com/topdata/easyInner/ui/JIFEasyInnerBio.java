//******************************************************************************
//A Topdata Sistemas de Automação Ltda não se responsabiliza por qualquer
//tipo de dano que este software possa causar, este exemplo deve ser utilizado
//apenas para demonstrar a comunicação com os equipamentos da linha Inner.
//
//Exemplo Biometria
//Desenvolvido em Java.
//                                           Topdata Sistemas de Automação Ltda.
//******************************************************************************

package com.topdata.easyInner.ui;

import com.topdata.easyInner.controller.EasyInnerBioController;
import java.util.logging.*;
import javax.swing.JOptionPane;

public class JIFEasyInnerBio extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;

    //Declaração de variáveis
    private final EasyInnerBioController bioController = new EasyInnerBioController(this);

    /**
     * ABERTURA FORMULÁRIO Carregamento das combos e campos
     *
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "unchecked"})
    public JIFEasyInnerBio() throws Exception {

        initComponents();

        //Carregamento das Combos
        //Combo Tipo Conexão
        jCboTipoConexao.removeAllItems();
        jCboTipoConexao.addItem("Serial");
        jCboTipoConexao.addItem("TCP/IP");
        jCboTipoConexao.addItem("TCP/IP porta fixa");
        jCboTipoConexao.setSelectedIndex(2);    //Default

        //Combo Tipo Leitor
        jCboTipoLeitor.removeAllItems();
        jCboTipoLeitor.addItem("Código Barras");
        jCboTipoLeitor.addItem("Magnético");
        jCboTipoLeitor.addItem("Prox. Abatrack/Smart Card");
        jCboTipoLeitor.addItem("Prox. Wiegand/Smart Card");
        jCboTipoLeitor.addItem("Prox. Smart Card Serial");
        jCboTipoLeitor.addItem("Codigo barras serial");
        jCboTipoLeitor.addItem("Wiegand FC sem zero");
        jCboTipoLeitor.setSelectedIndex(0);     //Default

        //Combo Padrão Cartão
        jCboPadraoCartao.removeAllItems();
        jCboPadraoCartao.addItem("Topdata");
        jCboPadraoCartao.addItem("Livre");
        jCboPadraoCartao.setSelectedIndex(1);   //Default

        //Inicialização campos
        jTxtNumInner.setText("1");
        jTxtQtdeDigitos.setText("16");
        jTxtPorta.setText("3570");

        bioController.carregaGrid();

        bioController.carregarHamster();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jTbpBiometria = new javax.swing.JTabbedPane();
        tbConfiguracao = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBtnReceberModelo = new javax.swing.JButton();
        jBtnReceberVersao = new javax.swing.JButton();
        jTxtNumInner = new javax.swing.JTextField();
        jTxtPorta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCboTipoConexao = new javax.swing.JComboBox();
        jCboPadraoCartao = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jCboTipoLeitor = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jTxtQtdeDigitos = new javax.swing.JTextField();
        chkHabVerificacao = new javax.swing.JCheckBox();
        chkHabIdentificacao = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        cmdConfigurarInner = new javax.swing.JButton();
        jChkHabilitaMaior10Digitos = new javax.swing.JCheckBox();
        jBtnEnviarAjustesBio = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        LblStatus = new javax.swing.JLabel();
        tbManutencaoUsuarioBio = new javax.swing.JDesktopPane();
        jPnlCMDDiretoInnerBio = new javax.swing.JPanel();
        jLblUsuario = new javax.swing.JLabel();
        jTxtUsuario = new javax.swing.JTextField();
        jBtnCadastrar = new javax.swing.JButton();
        jBtnSolicitarDigtal = new javax.swing.JButton();
        jPnlUsuariosSemBio = new javax.swing.JPanel();
        jBtnEnviarLista = new javax.swing.JButton();
        jScrUsuariosSemBio = new javax.swing.JScrollPane();
        jTblUsuarioSemBio = new javax.swing.JTable();
        jPnlUsuariosInner = new javax.swing.JPanel();
        jPgrStatus = new javax.swing.JProgressBar();
        jScrUsuariosInner = new javax.swing.JScrollPane();
        jTUsuariosInner = new javax.swing.JTable();
        jPnlCmd = new javax.swing.JPanel();
        jBtnExcluirSelecionado = new javax.swing.JButton();
        jBtnReceberQtdeUsuarios = new javax.swing.JButton();
        jBtnReceberTodosUsuarios = new javax.swing.JButton();
        jBtnGravarBase = new javax.swing.JButton();
        jPnlUsuariosBase = new javax.swing.JPanel();
        jBtnExcluirUsuarioBase = new javax.swing.JButton();
        jBtnEnviarTodos = new javax.swing.JButton();
        jScrUsuariosBase = new javax.swing.JScrollPane();
        jTblUsuariosBase = new javax.swing.JTable();
        jBtnEnviarSelecionado = new javax.swing.JButton();
        jPnlQtdDigital = new javax.swing.JPanel();
        jRdbDigital1 = new javax.swing.JRadioButton();
        jRdbDigital2 = new javax.swing.JRadioButton();
        jScrManutencao = new javax.swing.JScrollPane();
        jTxaManutencao = new javax.swing.JTextArea();
        pnlHamster = new javax.swing.JPanel();
        pnlConfQualidade = new javax.swing.JPanel();
        pnlCapHamster = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jCboDispositivos = new javax.swing.JComboBox();
        jBtnIniciar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTxtCartao = new javax.swing.JTextField();
        jBtnCapturar = new javax.swing.JButton();
        pnlConfiguracoesQual = new javax.swing.JPanel();
        chkPopup = new javax.swing.JCheckBox();
        sldValorVerify = new javax.swing.JSlider();
        lblMinVerify = new javax.swing.JLabel();
        lblValorVerify = new javax.swing.JLabel();
        sldValorDigital = new javax.swing.JSlider();
        lblMinDigital = new javax.swing.JLabel();
        lblValorDigital = new javax.swing.JLabel();
        jpnImagem = new javax.swing.JPanel();
        cnvImgDigital = new java.awt.Canvas();
        lblQualidadeImagem = new javax.swing.JLabel();
        lblValorQualImagem = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setTitle("Exemplo Teste Biometria");

        jTbpBiometria.setVerifyInputWhenFocusTarget(false);

        tbConfiguracao.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Número do inner:");

        jLabel5.setText("Porta:");

        jBtnReceberModelo.setText("Receber modelo bio");
        jBtnReceberModelo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnReceberModeloActionPerformed(evt);
            }
        });

        jBtnReceberVersao.setText("Receber versão bio");
        jBtnReceberVersao.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnReceberVersaoActionPerformed(evt);
            }
        });

        jTxtNumInner.setText("1");

        jTxtPorta.setText("3570");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(104, 104, 104)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtNumInner)
                    .addComponent(jTxtPorta, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addGap(81, 81, 81)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnReceberVersao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnReceberModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBtnReceberModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnReceberVersao))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTxtNumInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Tipo Conexão:");

        jLabel7.setText("Padrão Cartão:");

        jLabel8.setText("Tipo Leitor:");

        jLabel9.setText("Número de Dígitos:");

        chkHabVerificacao.setSelected(true);
        chkHabVerificacao.setText("Habilitar verificação biométrica");

        chkHabIdentificacao.setSelected(true);
        chkHabIdentificacao.setText("Habilitar identificação biométrica");

        jLabel1.setText("Biometria:");

        cmdConfigurarInner.setText("Configurar inner");
        cmdConfigurarInner.setPreferredSize(new java.awt.Dimension(127, 23));
        cmdConfigurarInner.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cmdConfigurarInnerActionPerformed(evt);
            }
        });

        jChkHabilitaMaior10Digitos.setText("Biometria mais de 10 digitos");

        jBtnEnviarAjustesBio.setText("Enviar Ajustes Bio");
        jBtnEnviarAjustesBio.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnEnviarAjustesBioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkHabIdentificacao, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(jCboPadraoCartao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCboTipoLeitor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCboTipoConexao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTxtQtdeDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmdConfigurarInner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkHabVerificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jChkHabilitaMaior10Digitos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(68, 68, 68)
                .addComponent(jBtnEnviarAjustesBio, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addGap(59, 59, 59))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtQtdeDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(jBtnEnviarAjustesBio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboTipoConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboTipoLeitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboPadraoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(chkHabVerificacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkHabIdentificacao))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addComponent(jChkHabilitaMaior10Digitos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdConfigurarInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel9.setBackground(new java.awt.Color(153, 255, 255));

        LblStatus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblStatus.setText("Selecione um comando.");
        LblStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblStatus)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tbConfiguracao.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbConfiguracao.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbConfiguracao.setLayer(jPanel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout tbConfiguracaoLayout = new javax.swing.GroupLayout(tbConfiguracao);
        tbConfiguracao.setLayout(tbConfiguracaoLayout);
        tbConfiguracaoLayout.setHorizontalGroup(
            tbConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbConfiguracaoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(tbConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        tbConfiguracaoLayout.setVerticalGroup(
            tbConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbConfiguracaoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTbpBiometria.addTab("Configurações", tbConfiguracao);

        tbManutencaoUsuarioBio.setBackground(new java.awt.Color(255, 255, 255));

        jPnlCMDDiretoInnerBio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLblUsuario.setText("Usuário:");

        jBtnCadastrar.setText("Cadastrar usuário pelo leitor biométrico Inner");
        jBtnCadastrar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnCadastrarActionPerformed(evt);
            }
        });

        jBtnSolicitarDigtal.setText("Solicitar digital pelo leitor biométrico Inner");
        jBtnSolicitarDigtal.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnSolicitarDigtalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlCMDDiretoInnerBioLayout = new javax.swing.GroupLayout(jPnlCMDDiretoInnerBio);
        jPnlCMDDiretoInnerBio.setLayout(jPnlCMDDiretoInnerBioLayout);
        jPnlCMDDiretoInnerBioLayout.setHorizontalGroup(
            jPnlCMDDiretoInnerBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlCMDDiretoInnerBioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlCMDDiretoInnerBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBtnSolicitarDigtal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnCadastrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnlCMDDiretoInnerBioLayout.createSequentialGroup()
                        .addComponent(jLblUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtUsuario)))
                .addContainerGap())
        );
        jPnlCMDDiretoInnerBioLayout.setVerticalGroup(
            jPnlCMDDiretoInnerBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCMDDiretoInnerBioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlCMDDiretoInnerBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblUsuario)
                    .addComponent(jTxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSolicitarDigtal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPnlUsuariosSemBio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnEnviarLista.setText("Enviar Lista Sem Digital");
        jBtnEnviarLista.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnEnviarListaActionPerformed(evt);
            }
        });

        jTblUsuarioSemBio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Usuário"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jTblUsuarioSemBio.setEditingRow(0);
        jScrUsuariosSemBio.setViewportView(jTblUsuarioSemBio);

        javax.swing.GroupLayout jPnlUsuariosSemBioLayout = new javax.swing.GroupLayout(jPnlUsuariosSemBio);
        jPnlUsuariosSemBio.setLayout(jPnlUsuariosSemBioLayout);
        jPnlUsuariosSemBioLayout.setHorizontalGroup(
            jPnlUsuariosSemBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlUsuariosSemBioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlUsuariosSemBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrUsuariosSemBio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jBtnEnviarLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPnlUsuariosSemBioLayout.setVerticalGroup(
            jPnlUsuariosSemBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlUsuariosSemBioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrUsuariosSemBio, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEnviarLista)
                .addContainerGap())
        );

        jPnlUsuariosInner.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuários Inner"));

        jTUsuariosInner.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Usuarios"
            }
        ));
        jScrUsuariosInner.setViewportView(jTUsuariosInner);

        jPnlCmd.setLayout(new java.awt.GridLayout(4, 1));

        jBtnExcluirSelecionado.setText("Excluir Selecionados");
        jBtnExcluirSelecionado.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnExcluirSelecionadoActionPerformed(evt);
            }
        });
        jPnlCmd.add(jBtnExcluirSelecionado);

        jBtnReceberQtdeUsuarios.setText("Receber Quantidade Usuários Bio");
        jBtnReceberQtdeUsuarios.setActionCommand("Receber Quantidade de Usuários Bio");
        jBtnReceberQtdeUsuarios.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnReceberQtdeUsuariosActionPerformed(evt);
            }
        });
        jPnlCmd.add(jBtnReceberQtdeUsuarios);

        jBtnReceberTodosUsuarios.setText("Receber Todos os Usuários");
        jBtnReceberTodosUsuarios.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnReceberTodosUsuariosActionPerformed(evt);
            }
        });
        jPnlCmd.add(jBtnReceberTodosUsuarios);

        jBtnGravarBase.setText("Receber Templates/Gravar na Base ");
        jBtnGravarBase.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnGravarBaseActionPerformed(evt);
            }
        });
        jPnlCmd.add(jBtnGravarBase);

        javax.swing.GroupLayout jPnlUsuariosInnerLayout = new javax.swing.GroupLayout(jPnlUsuariosInner);
        jPnlUsuariosInner.setLayout(jPnlUsuariosInnerLayout);
        jPnlUsuariosInnerLayout.setHorizontalGroup(
            jPnlUsuariosInnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlCmd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrUsuariosInner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPgrStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPnlUsuariosInnerLayout.setVerticalGroup(
            jPnlUsuariosInnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlUsuariosInnerLayout.createSequentialGroup()
                .addComponent(jScrUsuariosInner, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlCmd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPgrStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPnlUsuariosBase.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnExcluirUsuarioBase.setText("Excluir registro base de dados");
        jBtnExcluirUsuarioBase.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnExcluirUsuarioBaseActionPerformed(evt);
            }
        });

        jBtnEnviarTodos.setText("Enviar todos");
        jBtnEnviarTodos.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnEnviarTodosActionPerformed(evt);
            }
        });

        jTblUsuariosBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Cartão", "Template 1", "Template 2"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jTblUsuariosBase.getTableHeader().setReorderingAllowed(false);
        jScrUsuariosBase.setViewportView(jTblUsuariosBase);

        jBtnEnviarSelecionado.setText("Enviar selecionado");
        jBtnEnviarSelecionado.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnEnviarSelecionadoActionPerformed(evt);
            }
        });

        jPnlQtdDigital.setBorder(javax.swing.BorderFactory.createTitledBorder("Inner Versão > 5.xx"));

        jRdbDigital1.setText("1 Digital");
        jRdbDigital1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRdbDigital1ActionPerformed(evt);
            }
        });

        jRdbDigital2.setSelected(true);
        jRdbDigital2.setText("2 Digital");
        jRdbDigital2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRdbDigital2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlQtdDigitalLayout = new javax.swing.GroupLayout(jPnlQtdDigital);
        jPnlQtdDigital.setLayout(jPnlQtdDigitalLayout);
        jPnlQtdDigitalLayout.setHorizontalGroup(
            jPnlQtdDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlQtdDigitalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jRdbDigital1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRdbDigital2)
                .addGap(26, 26, 26))
        );
        jPnlQtdDigitalLayout.setVerticalGroup(
            jPnlQtdDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlQtdDigitalLayout.createSequentialGroup()
                .addGroup(jPnlQtdDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRdbDigital2)
                    .addComponent(jRdbDigital1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPnlUsuariosBaseLayout = new javax.swing.GroupLayout(jPnlUsuariosBase);
        jPnlUsuariosBase.setLayout(jPnlUsuariosBaseLayout);
        jPnlUsuariosBaseLayout.setHorizontalGroup(
            jPnlUsuariosBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlUsuariosBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlUsuariosBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPnlQtdDigital, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnExcluirUsuarioBase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrUsuariosBase, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnlUsuariosBaseLayout.createSequentialGroup()
                        .addComponent(jBtnEnviarSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnEnviarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPnlUsuariosBaseLayout.setVerticalGroup(
            jPnlUsuariosBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlUsuariosBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrUsuariosBase, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnExcluirUsuarioBase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlUsuariosBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnEnviarTodos)
                    .addComponent(jBtnEnviarSelecionado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlQtdDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTxaManutencao.setBackground(new java.awt.Color(153, 255, 255));
        jTxaManutencao.setColumns(20);
        jTxaManutencao.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jTxaManutencao.setLineWrap(true);
        jTxaManutencao.setText("Selecione um comando");
        jTxaManutencao.setAutoscrolls(false);
        jScrManutencao.setViewportView(jTxaManutencao);

        tbManutencaoUsuarioBio.setLayer(jPnlCMDDiretoInnerBio, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbManutencaoUsuarioBio.setLayer(jPnlUsuariosSemBio, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbManutencaoUsuarioBio.setLayer(jPnlUsuariosInner, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbManutencaoUsuarioBio.setLayer(jPnlUsuariosBase, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbManutencaoUsuarioBio.setLayer(jScrManutencao, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout tbManutencaoUsuarioBioLayout = new javax.swing.GroupLayout(tbManutencaoUsuarioBio);
        tbManutencaoUsuarioBio.setLayout(tbManutencaoUsuarioBioLayout);
        tbManutencaoUsuarioBioLayout.setHorizontalGroup(
            tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbManutencaoUsuarioBioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPnlCMDDiretoInnerBio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPnlUsuariosSemBio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrManutencao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlUsuariosInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlUsuariosBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tbManutencaoUsuarioBioLayout.setVerticalGroup(
            tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbManutencaoUsuarioBioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbManutencaoUsuarioBioLayout.createSequentialGroup()
                        .addComponent(jScrManutencao, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPnlCMDDiretoInnerBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPnlUsuariosSemBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPnlUsuariosInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPnlUsuariosBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jTbpBiometria.addTab("Manutenção Usuário Bio", tbManutencaoUsuarioBio);

        pnlConfQualidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlCapHamster.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("Dispositivo:");

        jCboDispositivos.addPopupMenuListener(new javax.swing.event.PopupMenuListener()
        {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt)
            {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt)
            {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt)
            {
                jCboDispositivos_click(evt);
            }
        });
        jCboDispositivos.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                jCboDispositivosItemStateChanged(evt);
            }
        });

        jBtnIniciar.setText("Iniciar");
        jBtnIniciar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnIniciarActionPerformed(evt);
            }
        });

        jLabel13.setText("Cartão:");

        jTxtCartao.setEnabled(false);

        jBtnCapturar.setText("Capturar");
        jBtnCapturar.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jBtnCapturar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnCapturarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCapHamsterLayout = new javax.swing.GroupLayout(pnlCapHamster);
        pnlCapHamster.setLayout(pnlCapHamsterLayout);
        pnlCapHamsterLayout.setHorizontalGroup(
            pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapHamsterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addGroup(pnlCapHamsterLayout.createSequentialGroup()
                        .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCboDispositivos, 0, 136, Short.MAX_VALUE)
                            .addComponent(jTxtCartao))
                        .addGap(18, 21, Short.MAX_VALUE)
                        .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnCapturar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlCapHamsterLayout.setVerticalGroup(
            pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapHamsterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(1, 1, 1)
                .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboDispositivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnIniciar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxtCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCapturar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlConfiguracoesQual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        chkPopup.setLabel("Popup");

        sldValorVerify.setValue(85);
        sldValorVerify.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                sldValorVerifyStateChanged(evt);
            }
        });

        lblMinVerify.setText("Valor minimo verify");

        lblValorVerify.setText("85");

        sldValorDigital.setMaximum(5);
        sldValorDigital.setValue(3);
        sldValorDigital.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                sldValorDigitalStateChanged(evt);
            }
        });

        lblMinDigital.setText("Valor minimo digital");

        lblValorDigital.setText("3");

        javax.swing.GroupLayout pnlConfiguracoesQualLayout = new javax.swing.GroupLayout(pnlConfiguracoesQual);
        pnlConfiguracoesQual.setLayout(pnlConfiguracoesQualLayout);
        pnlConfiguracoesQualLayout.setHorizontalGroup(
            pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblMinVerify)
                        .addGap(43, 43, 43)
                        .addComponent(lblValorVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkPopup))
                    .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                        .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sldValorVerify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMinDigital)
                                .addGap(43, 43, 43)
                                .addComponent(lblValorDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sldValorDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlConfiguracoesQualLayout.setVerticalGroup(
            pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkPopup)
                    .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMinVerify)
                            .addComponent(lblValorVerify))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sldValorVerify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMinDigital)
                    .addComponent(lblValorDigital))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sldValorDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout pnlConfQualidadeLayout = new javax.swing.GroupLayout(pnlConfQualidade);
        pnlConfQualidade.setLayout(pnlConfQualidadeLayout);
        pnlConfQualidadeLayout.setHorizontalGroup(
            pnlConfQualidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfQualidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConfQualidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCapHamster, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlConfiguracoesQual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlConfQualidadeLayout.setVerticalGroup(
            pnlConfQualidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfQualidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCapHamster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlConfiguracoesQual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jpnImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblQualidadeImagem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblQualidadeImagem.setText("Qualidade na imagem:");

        lblValorQualImagem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblValorQualImagem.setText("0");

        javax.swing.GroupLayout jpnImagemLayout = new javax.swing.GroupLayout(jpnImagem);
        jpnImagem.setLayout(jpnImagemLayout);
        jpnImagemLayout.setHorizontalGroup(
            jpnImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnImagemLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(cnvImgDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(jpnImagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQualidadeImagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblValorQualImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jpnImagemLayout.setVerticalGroup(
            jpnImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnImagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cnvImgDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jpnImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQualidadeImagem)
                    .addComponent(lblValorQualImagem))
                .addContainerGap(249, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHamsterLayout = new javax.swing.GroupLayout(pnlHamster);
        pnlHamster.setLayout(pnlHamsterLayout);
        pnlHamsterLayout.setHorizontalGroup(
            pnlHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHamsterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlConfQualidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );
        pnlHamsterLayout.setVerticalGroup(
            pnlHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHamsterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlConfQualidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlHamsterLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jpnImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTbpBiometria.addTab("Hamster", pnlHamster);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTbpBiometria, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTbpBiometria, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Receber Modelo Bio O modelo do Inner Bio é retornado.
     *
     * @param evt
     */
    private void jBtnReceberModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReceberModeloActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JOptionPane.showMessageDialog(rootPane, "Modelo placa FIM :" + bioController.solicitarModeloBio());
                } catch (InterruptedException ex) {
                    Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
    }//GEN-LAST:event_jBtnReceberModeloActionPerformed

    /**
     * Receber Versão Bio A versão do Inner Bio é retornado.
     *
     * @param evt
     */
    private void jBtnReceberVersaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReceberVersaoActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jTxaManutencao.append("Solicitando Versão bio...");
                    JOptionPane.showMessageDialog(rootPane, "versão placa FIM :" + bioController.solicitarVersaoBio());

                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao solicitar versão !" + ex);
                }
            }
        }).start();
    }//GEN-LAST:event_jBtnReceberVersaoActionPerformed

    /**
     * MONTAR CONFIGURAÇÃO INNER Método responsável em enviar as configurações
     * Inner e Inner Bio
     *
     * @param evt
     */
        private void cmdConfigurarInnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConfigurarInnerActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bioController.configurarInner();
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao enviar configurações " + ex.getMessage());
                    }
                }

            }).start();
        }//GEN-LAST:event_cmdConfigurarInnerActionPerformed

    /**
     * Adicionar Usuário na Memória Cadastra a 1º e 2º digital do usuário na
     * memória do Inner Bio.
     *
     * @param evt
     */
        private void jBtnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCadastrarActionPerformed
            //Mensagem de Status
            jTxaManutencao.setText("Cadastrando Usuário " + jTxtUsuario.getText());

            //Obrigatório
            if (jTxtUsuario.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o Nº do usuário para cadastro");
                return;
            }

            bioController.cadastrarPeloLeitorInner();

        }//GEN-LAST:event_jBtnCadastrarActionPerformed

    /**
     * Remover Usuário da Memória Verifica se o usuário existe, se sim, exclui e
     * retorna.
     *
     * @param evt
     */
        private void jBtnSolicitarDigtalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSolicitarDigtalActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Mensagem de Status
                        jTxaManutencao.setText("Solicitando digital leitor biométrico Inner");
                        bioController.solicitarDigitalLeitorInner();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    }
                }
            }).start();
        }//GEN-LAST:event_jBtnSolicitarDigtalActionPerformed

    /**
     * Exclui o usuario selecionado da base
     *
     * @param evt
     */
        private void jBtnExcluirUsuarioBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirUsuarioBaseActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bioController.excluirUsuarioBase();
                }
            }).start();
        }//GEN-LAST:event_jBtnExcluirUsuarioBaseActionPerformed

    /**
     * Envia todos os usuário do JTable para o Inner
     *
     * @param evt
     */
        private void jBtnEnviarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnviarTodosActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (JIFEasyInnerBio.this) {
                        try {
                            Thread.currentThread().setName("EnviarTodosUsuario");
                            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                            bioController.EnviarTodosUsuario();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();  
        }//GEN-LAST:event_jBtnEnviarTodosActionPerformed

    /**
     * Envio Lista de Usuários sem digital
     *
     * @param evt
     */
        private void jBtnEnviarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnviarListaActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bioController.enviarListaSemBio();
                }
            }).start();
        }//GEN-LAST:event_jBtnEnviarListaActionPerformed

    /**
     * Gravar Base Grava os usuários na base de dados do computador
     *
     * @param evt
     */
    private void jBtnGravarBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGravarBaseActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Se a lista de usuários estiver vazia finaliza
                if (jTUsuariosInner.getModel().getRowCount() == 0) {
                    return;
                }
                try {
                    jTxaManutencao.setText("Solicitando Usuários...");
                    bioController.gravarNaBase();
                   
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }).start();
    }//GEN-LAST:event_jBtnGravarBaseActionPerformed

    /**
     * Receber Usuários cadastrados no Inner Bio Retorna todos os usuários
     * cadastrados
     *
     * @param evt
     */
    private void jBtnReceberTodosUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReceberTodosUsuariosActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                jTxaManutencao.setText("Recebendo todos os Usuários...");
                try {
                    bioController.solicitarUsuariosPlacaFIM();
                } catch (Exception ex) {
                    Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }).start();
    }//GEN-LAST:event_jBtnReceberTodosUsuariosActionPerformed

    /**
     * Receber Usuários cadastrados no Inner Bio Retorna a quantidade de
     * usuários cadastrados.
     *
     * @param evt
     */
    private void jBtnReceberQtdeUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReceberQtdeUsuariosActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Mensagem Status
                    jTxaManutencao.setText("Recebendo quantidade de usuários cadastrados...");
                    Integer total = bioController.receberQuantidadeUsuariosBio();
                    JOptionPane.showMessageDialog(rootPane, "Total de usuários placa FIM : " + total.toString());
                    jTxaManutencao.setText("Total de Usuários : " + total.toString());
                } catch (InterruptedException ex) {
                    Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }//GEN-LAST:event_jBtnReceberQtdeUsuariosActionPerformed

    /**
     * Envia somente o usuário selecionado no JTable
     * @param evt 
     */
    private void jBtnEnviarSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnviarSelecionadoActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().setName("EnviarUsuarioSelecionado");
                    bioController.EnviarUsuarioSelecionado();
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao Enviar o usuário !" + ex);
                }
            }
        }).start();
    }//GEN-LAST:event_jBtnEnviarSelecionadoActionPerformed

    /**
     * Exclui os usuarios selecionados no JTable
     * @param evt 
     */
    private void jBtnExcluirSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirSelecionadoActionPerformed
        try {
            Thread.currentThread().setName("ExcluirUsuarioBioSelecionado");
            bioController.excluirUsuariosBio();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_jBtnExcluirSelecionadoActionPerformed

    private void jRdbDigital2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdbDigital2ActionPerformed
        jRdbDigital1.setSelected(!jRdbDigital2.isSelected());
    }//GEN-LAST:event_jRdbDigital2ActionPerformed

    private void jRdbDigital1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdbDigital1ActionPerformed
        jRdbDigital2.setSelected(!jRdbDigital1.isSelected());
    }//GEN-LAST:event_jRdbDigital1ActionPerformed

    private void jBtnEnviarAjustesBioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnviarAjustesBioActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                jTxaManutencao.append("Enviando Ajustes Bio...");
                bioController.EnviarAjustesBiometricos();
            }
        }).start();
    }//GEN-LAST:event_jBtnEnviarAjustesBioActionPerformed

    private void jCboDispositivos_click(javax.swing.event.PopupMenuEvent evt)//GEN-FIRST:event_jCboDispositivos_click
    {//GEN-HEADEREND:event_jCboDispositivos_click
        if (jCboDispositivos.getItemCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Hamster não foi conectado ou o driver não foi instalado, para maiores detalhes acesse o arquivo \n leiame contido na instalação do SDK (Menu Iniciar/Programas/SDK EasyInner/Manuais)");
        }
    }//GEN-LAST:event_jCboDispositivos_click

    private void jCboDispositivosItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_jCboDispositivosItemStateChanged
    {//GEN-HEADEREND:event_jCboDispositivosItemStateChanged
        if (jCboDispositivos.getSelectedIndex() >= 0)
        {
            jBtnIniciar.setEnabled(true);
        } else
        {
            jBtnIniciar.setEnabled(false);
        }
    }//GEN-LAST:event_jCboDispositivosItemStateChanged

    private void jBtnIniciarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnIniciarActionPerformed
    {//GEN-HEADEREND:event_jBtnIniciarActionPerformed
        //Obrigatório
        if (jCboDispositivos.getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(null, "Não foi selecionado nenhum dispositivo");
        }
        bioController.carregarHamster();
        jTxtCartao.setEnabled(true);
    }//GEN-LAST:event_jBtnIniciarActionPerformed

    private void jBtnCapturarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnCapturarActionPerformed
    {//GEN-HEADEREND:event_jBtnCapturarActionPerformed

        //Obrigatório
        if (jTxtCartao.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Favor informar o número do cartão!");
            return;
        }

        try
        {
            bioController.cadastrarDigitalHamster();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jBtnCapturarActionPerformed

    private void sldValorDigitalStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_sldValorDigitalStateChanged
    {//GEN-HEADEREND:event_sldValorDigitalStateChanged
        lblValorDigital.setText(Integer.toString(sldValorDigital.getValue()));
    }//GEN-LAST:event_sldValorDigitalStateChanged

    private void sldValorVerifyStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_sldValorVerifyStateChanged
    {//GEN-HEADEREND:event_sldValorVerifyStateChanged
        lblValorVerify.setText(Integer.toString(sldValorVerify.getValue()));
    }//GEN-LAST:event_sldValorVerifyStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel LblStatus;
    public javax.swing.JCheckBox chkHabIdentificacao;
    public javax.swing.JCheckBox chkHabVerificacao;
    public javax.swing.JCheckBox chkPopup;
    public javax.swing.JButton cmdConfigurarInner;
    public java.awt.Canvas cnvImgDigital;
    private javax.swing.JButton jBtnCadastrar;
    public javax.swing.JButton jBtnCapturar;
    public javax.swing.JButton jBtnEnviarAjustesBio;
    private javax.swing.JButton jBtnEnviarLista;
    private javax.swing.JButton jBtnEnviarSelecionado;
    private javax.swing.JButton jBtnEnviarTodos;
    private javax.swing.JButton jBtnExcluirSelecionado;
    private javax.swing.JButton jBtnExcluirUsuarioBase;
    private javax.swing.JButton jBtnGravarBase;
    public javax.swing.JButton jBtnIniciar;
    public javax.swing.JButton jBtnReceberModelo;
    private javax.swing.JButton jBtnReceberQtdeUsuarios;
    private javax.swing.JButton jBtnReceberTodosUsuarios;
    public javax.swing.JButton jBtnReceberVersao;
    private javax.swing.JButton jBtnSolicitarDigtal;
    public javax.swing.JComboBox jCboDispositivos;
    public javax.swing.JComboBox jCboPadraoCartao;
    public javax.swing.JComboBox jCboTipoConexao;
    public javax.swing.JComboBox jCboTipoLeitor;
    public javax.swing.JCheckBox jChkHabilitaMaior10Digitos;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblUsuario;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel9;
    public javax.swing.JProgressBar jPgrStatus;
    private javax.swing.JPanel jPnlCMDDiretoInnerBio;
    private javax.swing.JPanel jPnlCmd;
    private javax.swing.JPanel jPnlQtdDigital;
    private javax.swing.JPanel jPnlUsuariosBase;
    private javax.swing.JPanel jPnlUsuariosInner;
    private javax.swing.JPanel jPnlUsuariosSemBio;
    public javax.swing.JRadioButton jRdbDigital1;
    public javax.swing.JRadioButton jRdbDigital2;
    private javax.swing.JScrollPane jScrManutencao;
    private javax.swing.JScrollPane jScrUsuariosBase;
    private javax.swing.JScrollPane jScrUsuariosInner;
    private javax.swing.JScrollPane jScrUsuariosSemBio;
    public javax.swing.JTable jTUsuariosInner;
    public javax.swing.JTable jTblUsuarioSemBio;
    public javax.swing.JTable jTblUsuariosBase;
    public javax.swing.JTabbedPane jTbpBiometria;
    public javax.swing.JTextArea jTxaManutencao;
    public javax.swing.JTextField jTxtCartao;
    public javax.swing.JTextField jTxtNumInner;
    public javax.swing.JTextField jTxtPorta;
    public javax.swing.JTextField jTxtQtdeDigitos;
    public javax.swing.JTextField jTxtUsuario;
    public javax.swing.JPanel jpnImagem;
    private javax.swing.JLabel lblMinDigital;
    private javax.swing.JLabel lblMinVerify;
    private javax.swing.JLabel lblQualidadeImagem;
    private javax.swing.JLabel lblValorDigital;
    public javax.swing.JLabel lblValorQualImagem;
    private javax.swing.JLabel lblValorVerify;
    private javax.swing.JPanel pnlCapHamster;
    public javax.swing.JPanel pnlConfQualidade;
    public javax.swing.JPanel pnlConfiguracoesQual;
    public javax.swing.JPanel pnlHamster;
    public javax.swing.JSlider sldValorDigital;
    public javax.swing.JSlider sldValorVerify;
    private javax.swing.JDesktopPane tbConfiguracao;
    private javax.swing.JDesktopPane tbManutencaoUsuarioBio;
    // End of variables declaration//GEN-END:variables

}
