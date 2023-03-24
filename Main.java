import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Main
{
	public static JFrame appWindow = new JFrame();
	public static JPanel screen = new JPanel();
	public static JLabel text1 = new JLabel();
	public static JLabel text2 = new JLabel();
	public static JTextField textBox = new JTextField();
	public static JButton button1 = new JButton();
	public static JButton button2 = new JButton();
	public static ScreenLayout screenLayout = new ScreenLayout();
	public static RuntimeStatus runtimeStatus = RuntimeStatus.INPUT;

	public static ActionListener actionManager = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String command = event.getActionCommand();

			if(command.equals("BUTTON_1"))
			{
				if(runtimeStatus == RuntimeStatus.INPUT)
				{
					runtimeStatus = RuntimeStatus.CHOOSEFORMAT;
					paintChooseFormatScreen();
					return;
				}
				else if(runtimeStatus == RuntimeStatus.CHOOSEFORMAT)
				{
					if(stringToHex())
					{
						runtimeStatus = RuntimeStatus.OUTPUT;
						paintOutputScreen();
					}
					else
					{
						runtimeStatus = RuntimeStatus.ERROR;
						paintErrorScreen();
					}

					return;
				}
				else
				{
					runtimeStatus = RuntimeStatus.INPUT;
					paintInputScreen();
				}
			}
			else if(command.equals("BUTTON_2"))
			{
				if(runtimeStatus == RuntimeStatus.CHOOSEFORMAT)
				{
					if(stringToDec())
					{
						runtimeStatus = RuntimeStatus.OUTPUT;
						paintOutputScreen();
					}
					else
					{
						runtimeStatus = RuntimeStatus.ERROR;
						paintErrorScreen();
					}

					return;
				}
			}
		}
	};

	public static boolean stringToHex()
	{
		String inputText = textBox.getText();
		String outputText = "";

		int len = inputText.length();
		if(len == 0) return false;

		char[] cArr = inputText.toCharArray();

		int nchar = 0;
		while(nchar < len)
		{
			outputText += String.format("0x%X", (int) cArr[nchar]);
			outputText += " , ";
			nchar++;
		}

		outputText += "NULL";
		text2.setText(outputText);
		return true;
	}

	public static boolean stringToDec()
	{
		String inputText = textBox.getText();
		String outputText = "";

		int len = inputText.length();
		if(len == 0) return false;

		char[] cArr = inputText.toCharArray();

		int nchar = 0;
		while(nchar < len)
		{
			outputText += (int) cArr[nchar];
			outputText += " , ";
			nchar++;
		}

		outputText += "NULL";
		text2.setText(outputText);
		return true;
	}

	public static void paintInputScreen()
	{
		text1.setText("Enter text");
		text1.setVisible(true);

		text2.setVisible(false);

		textBox.setText("");
		textBox.setVisible(true);

		button1.setText("Convert");
		button1.setLocation(310, 200);
		button1.setSize(100, 20);
		button1.setVisible(true);

		button2.setVisible(false);
	}

	public static void paintChooseFormatScreen()
	{
		text1.setText("Choose Output Format");
		text1.setVisible(true);

		text2.setVisible(false);
		textBox.setVisible(false);

		button1.setText("Hexadecimal");
		button1.setLocation(230, 200);
		button1.setSize(120, 20);
		button1.setVisible(true);

		button2.setText("Decimal");
		button2.setLocation(370, 200);
		button2.setSize(120, 20);
		button2.setVisible(true);
	}

	public static void paintOutputScreen()
	{
		text1.setText("Result:");
		text1.setVisible(true);

		text2.setVisible(true);

		textBox.setVisible(false);

		button1.setText("Return");
		button1.setLocation(310, 280);
		button1.setSize(100, 20);
		button1.setVisible(true);

		button2.setVisible(false);
	}

	public static void paintErrorScreen()
	{
		text1.setText("Error: Invalid input");
		text1.setVisible(true);

		text2.setVisible(false);
		textBox.setVisible(false);

		button1.setText("Return");
		button1.setLocation(310, 200);
		button1.setSize(100, 20);
		button1.setVisible(true);

		button2.setVisible(false);
	}

	public static void main(String[] args)
	{
		text1.setVisible(false);
		text1.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		text1.setForeground(Color.BLACK);
		text1.setLayout(screenLayout);
		text1.setLocation(60, 50);
		text1.setSize(600, 30);
		text1.setHorizontalAlignment(JLabel.CENTER);

		text2.setVisible(false);
		text2.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
		text2.setForeground(Color.BLACK);
		text2.setLayout(screenLayout);
		text2.setLocation(60, 90);
		text2.setSize(600, 180);
		text2.setHorizontalAlignment(JLabel.LEFT);

		textBox.setVisible(false);
		textBox.setLayout(screenLayout);
		textBox.setLocation(60, 100);
		textBox.setSize(600, 60);

		button1.setVisible(false);
		button1.setForeground(Color.BLACK);
		button1.setBackground(Color.WHITE);
		button1.addActionListener(actionManager);
		button1.setActionCommand("BUTTON_1");
		button1.setLayout(screenLayout);

		button2.setVisible(false);
		button2.setForeground(Color.BLACK);
		button2.setBackground(Color.WHITE);
		button2.addActionListener(actionManager);
		button2.setActionCommand("BUTTON_2");
		button2.setLayout(screenLayout);

		screen.setVisible(true);
		screen.setSize(720, 360);
		screen.setBackground(Color.LIGHT_GRAY);
		screen.setLayout(screenLayout);
		screen.add(text1);
		screen.add(text2);
		screen.add(textBox);
		screen.add(button1);
		screen.add(button2);

		runtimeStatus = RuntimeStatus.INPUT;
		paintInputScreen();

		appWindow.setTitle("JRE Unicode Character Dictionary");
		appWindow.setSize(720, 360);
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindow.add(screen);
		appWindow.setVisible(true);
	}
}