import java.io.*;
import java.awt.*;
import java.awt.event.*;
public class frame extends Frame implements ActionListener,TextListener{
	public TextArea t;
	public static String fname,text;
	TextField tf;
	MenuItem nm,om,sm,asm;
	Menu m;MenuBar f;
	int count;
	frame abs;
	Dialog d;
frame()
{
	abs=this;
	count=5;
	setVisible(true);
	setSize(300,400);
	setTitle("IT_B : NOTE PAD");
	f=new MenuBar();
	m=new Menu("FILE");
	nm=new MenuItem("NEW");
	om=new MenuItem("OPEN");
	sm=new MenuItem("SAVE");
	asm=new MenuItem("SAVE AS");
	m.add(nm);
	m.add(om);
	m.add(sm);
	m.add(asm);
	f.add(m);
	setLayout(new FlowLayout());
	addWindowListener(new lis());
	//Button b=new Button("click");
	t=new TextArea("",50,100,1);
	t.addTextListener(this);
        add(t);
	setMenuBar(f);
       nm.addActionListener(this);
       om.addActionListener(this);
       sm.addActionListener(this);
       asm.addActionListener(this);
       	System.out.println("main");
}
public void textValueChanged(TextEvent e)
{
	count=1;System.out.println(count+"t");
}
public void actionPerformed(ActionEvent e)
	{
                   text=e.getActionCommand();  
		String a=t.getText();
		try{
			if(((text.equals("NEW"))||(text.equals("OPEN")))&&(count==1))
			{
				new myf("NOT SAVED","OK");
			}
		        if(text.equals("NEW")&& ((count==5)||(count==0)))
			{
				t.setText("");
				System.out.println(count+"n");
				fname="";
				abs.setTitle("IT_B : NOTE PAD");
				count=0;
			}
			if((text.equals("OPEN"))&& (count!=1))
			{
                         new myf("OPEN","OPEN FILE");
			 fun(a,text);
                          	abs.setTitle(fname);
					 count=0;
				System.out.println(count+"o");
			 System.out.println(fname);
		
			}
		       if(text.equals("SAVE")&&count==1)
			{
                                if(fname.equals("")){
                                 count=2;
				}else{
				fun(a,text);
				count=5;
				}
				System.out.println(count+" save");

			}
		       if(text.equals("SAVE AS")||count==2)
			{
                           new myf("SAVE AS","SAVE");
			   System.out.println(count+"save as");
				fun(a,text);
				abs.setTitle(fname);
			        count=5;
			}
	System.out.println("jhdhd");	}

		
		catch(IOException j){}
	  }
        void fun(String a,String check) throws IOException
       {
	        if(!check.equals("OPEN")){
	       OutputStream fo=new FileOutputStream(fname);
              BufferedOutputStream fko=new BufferedOutputStream(fo);
	       byte b[]=a.getBytes();
	       fko.write(b);
		fko.flush();
		fo.close();}
		 if((!check.equals("SAVE"))&& (!check.equals("SAVE AS"))){
		InputStream fi=new FileInputStream(fname);
		BufferedInputStream fk=new BufferedInputStream(fi);
		byte by[]=new byte[fi.available()];
		int n=fk.read(by);
		String s=new String(by);
               t.setText(s);
		 fi.close();}
		 count=0;
       }        
public static void main(String args[])
{
	frame f=new frame();
}
}
class lis extends WindowAdapter{
	public void windowActivated(WindowEvent e){System.out.println("activated");}
public void windowClosing(WindowEvent e){System.out.println("closing"); System.exit(0);}
public void windowDeactivated(WindowEvent e){System.out.println("deactiva");}
public void windowDeiconified(WindowEvent e){System.out.println("deconified");}
public void windowIconified(WindowEvent e){System.out.println("iconif");}	
public void windowOpened(WindowEvent e){System.out.println("ope");}
	public void windowClosed(WindowEvent e)
	{{System.out.println("closed");
	}}}
class myf implements ActionListener
{	Dialog d;TextField tf;Button b1;String text;
	public void actionPerformed(ActionEvent e)
	{
		if(!text.equals("OK")){
		String s=tf.getText()+".txt";
		frame.fname=s;
		}
	  d.setVisible(false);
	  }
	myf(String dname,String bname){
		text=bname;
                         d=new Dialog(new Frame(),dname,true);
			 Label l1=new Label();
			 if(dname.equals("NOT SAVED"))
			 {
				 l1.setText("FILE NOT SAVED, SAVE THE FILE");
				 l1.setBounds(10,100,100,100);
			 }else{
				 l1.setText("FILE NAME :");
                         tf=new TextField();
			 tf.setBounds(50,150,100,30);
			 l1.setBounds(10,100,60,30);
				 d.add(tf);
			 }
			 Button b1=new Button(bname);
			 d.setSize(300,300);
			 d.add(l1);
			 b1.setBounds(70,200,100,70);
			 d.setLayout(null);
			 d.add(b1);
			 b1.addActionListener(this);
			 d.setVisible(true);
	}
}

