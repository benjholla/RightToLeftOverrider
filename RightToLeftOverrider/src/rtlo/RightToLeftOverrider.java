package rtlo;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RightToLeftOverrider extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField filenamePrefixField;
	private JTextField displayExtensionField;
	private JTextField rightToLeftOveridePreviewField;
	
	private static File inputFile = null;
	private static File outputDirectory = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RightToLeftOverrider frame = new RightToLeftOverrider();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RightToLeftOverrider() {
		setTitle("Right-To-Left Overrider");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel inputButtonsPanel = new JPanel();
		GridBagConstraints gbc_inputButtonsPanel = new GridBagConstraints();
		gbc_inputButtonsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_inputButtonsPanel.fill = GridBagConstraints.BOTH;
		gbc_inputButtonsPanel.gridx = 0;
		gbc_inputButtonsPanel.gridy = 0;
		contentPane.add(inputButtonsPanel, gbc_inputButtonsPanel);
		GridBagLayout gbl_inputButtonsPanel = new GridBagLayout();
		gbl_inputButtonsPanel.columnWidths = new int[]{0, 0, 0};
		gbl_inputButtonsPanel.rowHeights = new int[]{0, 0};
		gbl_inputButtonsPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_inputButtonsPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		inputButtonsPanel.setLayout(gbl_inputButtonsPanel);
		
		JPanel inputFilePanel = new JPanel();
		GridBagConstraints gbc_inputFilePanel = new GridBagConstraints();
		gbc_inputFilePanel.insets = new Insets(0, 0, 0, 5);
		gbc_inputFilePanel.fill = GridBagConstraints.BOTH;
		gbc_inputFilePanel.gridx = 0;
		gbc_inputFilePanel.gridy = 0;
		inputButtonsPanel.add(inputFilePanel, gbc_inputFilePanel);
		GridBagLayout gbl_inputFilePanel = new GridBagLayout();
		gbl_inputFilePanel.columnWidths = new int[]{0, 0};
		gbl_inputFilePanel.rowHeights = new int[]{0, 0, 0};
		gbl_inputFilePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_inputFilePanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		inputFilePanel.setLayout(gbl_inputFilePanel);
		
		JButton inputFileButton = new JButton("Input File...");
		GridBagConstraints gbc_inputFileButton = new GridBagConstraints();
		gbc_inputFileButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputFileButton.insets = new Insets(0, 0, 5, 0);
		gbc_inputFileButton.gridx = 0;
		gbc_inputFileButton.gridy = 0;
		inputFilePanel.add(inputFileButton, gbc_inputFileButton);
		
		final JLabel inputFileLabel = new JLabel(" ");
		GridBagConstraints gbc_inputFileLabel = new GridBagConstraints();
		gbc_inputFileLabel.anchor = GridBagConstraints.WEST;
		gbc_inputFileLabel.gridx = 0;
		gbc_inputFileLabel.gridy = 1;
		inputFilePanel.add(inputFileLabel, gbc_inputFileLabel);
		
		JPanel outputDirectoryPanel = new JPanel();
		GridBagConstraints gbc_outputDirectoryPanel = new GridBagConstraints();
		gbc_outputDirectoryPanel.fill = GridBagConstraints.BOTH;
		gbc_outputDirectoryPanel.gridx = 1;
		gbc_outputDirectoryPanel.gridy = 0;
		inputButtonsPanel.add(outputDirectoryPanel, gbc_outputDirectoryPanel);
		GridBagLayout gbl_outputDirectoryPanel = new GridBagLayout();
		gbl_outputDirectoryPanel.columnWidths = new int[]{0, 0};
		gbl_outputDirectoryPanel.rowHeights = new int[]{0, 0, 0};
		gbl_outputDirectoryPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_outputDirectoryPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		outputDirectoryPanel.setLayout(gbl_outputDirectoryPanel);
		
		JButton outputDirectoryButton = new JButton("Output Dir...");
		GridBagConstraints gbc_outputDirectoryButton = new GridBagConstraints();
		gbc_outputDirectoryButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_outputDirectoryButton.insets = new Insets(0, 0, 5, 0);
		gbc_outputDirectoryButton.gridx = 0;
		gbc_outputDirectoryButton.gridy = 0;
		outputDirectoryPanel.add(outputDirectoryButton, gbc_outputDirectoryButton);
		
		final JLabel outputFileLabel = new JLabel(" ");
		GridBagConstraints gbc_outputFileLabel = new GridBagConstraints();
		gbc_outputFileLabel.anchor = GridBagConstraints.WEST;
		gbc_outputFileLabel.gridx = 0;
		gbc_outputFileLabel.gridy = 1;
		outputDirectoryPanel.add(outputFileLabel, gbc_outputFileLabel);
		
		JPanel filenameInputPanel = new JPanel();
		GridBagConstraints gbc_filenameInputPanel = new GridBagConstraints();
		gbc_filenameInputPanel.fill = GridBagConstraints.BOTH;
		gbc_filenameInputPanel.gridx = 0;
		gbc_filenameInputPanel.gridy = 1;
		contentPane.add(filenameInputPanel, gbc_filenameInputPanel);
		GridBagLayout gbl_filenameInputPanel = new GridBagLayout();
		gbl_filenameInputPanel.columnWidths = new int[]{0, 0};
		gbl_filenameInputPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_filenameInputPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_filenameInputPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		filenameInputPanel.setLayout(gbl_filenameInputPanel);
		
		JLabel filenamePrefixLabel = new JLabel(" Filename Prefix:");
		GridBagConstraints gbc_filenamePrefixLabel = new GridBagConstraints();
		gbc_filenamePrefixLabel.anchor = GridBagConstraints.WEST;
		gbc_filenamePrefixLabel.insets = new Insets(0, 0, 5, 0);
		gbc_filenamePrefixLabel.gridx = 0;
		gbc_filenamePrefixLabel.gridy = 0;
		filenameInputPanel.add(filenamePrefixLabel, gbc_filenamePrefixLabel);
		
		filenamePrefixField = new JTextField();
		filenamePrefixField.setEnabled(false);
		GridBagConstraints gbc_filenamePrefixField = new GridBagConstraints();
		gbc_filenamePrefixField.insets = new Insets(0, 0, 5, 0);
		gbc_filenamePrefixField.fill = GridBagConstraints.HORIZONTAL;
		gbc_filenamePrefixField.gridx = 0;
		gbc_filenamePrefixField.gridy = 1;
		filenameInputPanel.add(filenamePrefixField, gbc_filenamePrefixField);
		filenamePrefixField.setColumns(10);
		
		JLabel displayExtensionLabel = new JLabel(" Display Extension:");
		GridBagConstraints gbc_displayExtensionLabel = new GridBagConstraints();
		gbc_displayExtensionLabel.insets = new Insets(0, 0, 5, 0);
		gbc_displayExtensionLabel.anchor = GridBagConstraints.WEST;
		gbc_displayExtensionLabel.gridx = 0;
		gbc_displayExtensionLabel.gridy = 2;
		filenameInputPanel.add(displayExtensionLabel, gbc_displayExtensionLabel);
		
		displayExtensionField = new JTextField();
		displayExtensionField.setEnabled(false);
		GridBagConstraints gbc_displayExtensionField = new GridBagConstraints();
		gbc_displayExtensionField.insets = new Insets(0, 0, 5, 0);
		gbc_displayExtensionField.fill = GridBagConstraints.HORIZONTAL;
		gbc_displayExtensionField.gridx = 0;
		gbc_displayExtensionField.gridy = 3;
		filenameInputPanel.add(displayExtensionField, gbc_displayExtensionField);
		displayExtensionField.setColumns(10);
		
		JLabel rightToLeftOveridePreviewLabel = new JLabel(" Right-To-Left Override Preview:");
		GridBagConstraints gbc_rightToLeftOveridePreviewLabel = new GridBagConstraints();
		gbc_rightToLeftOveridePreviewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_rightToLeftOveridePreviewLabel.anchor = GridBagConstraints.WEST;
		gbc_rightToLeftOveridePreviewLabel.gridx = 0;
		gbc_rightToLeftOveridePreviewLabel.gridy = 4;
		filenameInputPanel.add(rightToLeftOveridePreviewLabel, gbc_rightToLeftOveridePreviewLabel);
		
		rightToLeftOveridePreviewField = new JTextField();
		rightToLeftOveridePreviewField.setEditable(false);
		GridBagConstraints gbc_rightToLeftOveridePreviewField = new GridBagConstraints();
		gbc_rightToLeftOveridePreviewField.insets = new Insets(0, 0, 5, 0);
		gbc_rightToLeftOveridePreviewField.fill = GridBagConstraints.HORIZONTAL;
		gbc_rightToLeftOveridePreviewField.gridx = 0;
		gbc_rightToLeftOveridePreviewField.gridy = 5;
		filenameInputPanel.add(rightToLeftOveridePreviewField, gbc_rightToLeftOveridePreviewField);
		rightToLeftOveridePreviewField.setColumns(10);
		
		final JButton copyAndRenameInputFileButton = new JButton("Copy and Rename Input File");
		copyAndRenameInputFileButton.setEnabled(false);
		GridBagConstraints gbc_copyAndRenameInputFileButton = new GridBagConstraints();
		gbc_copyAndRenameInputFileButton.gridx = 0;
		gbc_copyAndRenameInputFileButton.gridy = 6;
		filenameInputPanel.add(copyAndRenameInputFileButton, gbc_copyAndRenameInputFileButton);
		
		inputFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(RightToLeftOverrider.this);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                inputFile = fc.getSelectedFile();
	                inputFileLabel.setText(inputFile.getAbsolutePath());
	                filenamePrefixField.setText(RTLO.getFilePrefix(inputFile.getName()));
	                updatePreviewFields(copyAndRenameInputFileButton);
	            }
			}
		});
		
		outputDirectoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(RightToLeftOverrider.this);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                outputDirectory = fc.getSelectedFile();
	                outputFileLabel.setText(outputDirectory.getAbsolutePath());
	                updatePreviewFields(copyAndRenameInputFileButton);
	            }
			}
		});
		
		filenamePrefixField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				updatePreviewFields(copyAndRenameInputFileButton);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				updatePreviewFields(copyAndRenameInputFileButton);
			}
		});
		
		displayExtensionField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				updatePreviewFields(copyAndRenameInputFileButton);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				updatePreviewFields(copyAndRenameInputFileButton);
			}
		});
		
		copyAndRenameInputFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filenamePrefix = filenamePrefixField.getText().trim();
				String inputExtension = RTLO.getFileExtension(inputFile.getName()).trim();
				String displayExtension = displayExtensionField.getText().trim();
				String rtlo = RTLO.rtlo(filenamePrefix, inputExtension, displayExtension);
				File outputFile = new File(outputDirectory.getAbsolutePath() + File.separatorChar + rtlo);
				JFrame dialog = new JFrame();
				dialog.setLocationRelativeTo(null);
				try {
					copyFile(inputFile, outputFile);
					JOptionPane.showMessageDialog(dialog, "Success!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(dialog, "Could not copy file. Error: " + e1.getMessage());
				}
			}
		});
	}
	
	private void updatePreviewFields(final JButton copyAndRenameInputFileButton) {
		String filenamePrefix = filenamePrefixField.getText().trim();
		String inputExtension = RTLO.getFileExtension(inputFile.getName()).trim();
		String displayExtension = displayExtensionField.getText().trim();
		rightToLeftOveridePreviewField.setText(RTLO.rtloPreview(filenamePrefix, inputExtension, displayExtension));
		
		if(inputFile != null && outputDirectory != null){
			filenamePrefixField.setEnabled(true);
			displayExtensionField.setEnabled(true);
			if(!filenamePrefixField.getText().trim().equals("") && !displayExtensionField.getText().trim().equals("")){
				copyAndRenameInputFileButton.setEnabled(true);
			} else {
				copyAndRenameInputFileButton.setEnabled(false);
			}
		} else {
			filenamePrefixField.setEnabled(false);
			displayExtensionField.setEnabled(false);
		}
	}
	
	private static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

}
