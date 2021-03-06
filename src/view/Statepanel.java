package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entity.Player;
import entity.weapon.Weapon;


//状态类

public class Statepanel extends JPanel{
    
    public Player player;  
    Image image = (new ImageIcon("./util/主角头像.png")).getImage();//人物头像图标
    
    public Statepanel(){
        this.player = Player.getInstance();
        this.setSize(200, 600);
    }
    
    protected void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 200, 600);
        g.setColor(Color.BLACK);
        g.drawImage(image, 45, 0, this);
        
        Weapon weapon = player.getWeapon_onUse();
        
        g.drawString("名字： "+player.getName(), 10, 150);//名字
        g.drawString("等级："+player.getLevel(), 10, 175);
        g.drawString("力量 : "+player.getStrength(), 10, 200);
        g.drawString("敏捷: "+player.getAgility(), 10, 225);
        g.drawString("智力: "+player.getIntelligence(), 10, 250);
        
        g.drawString("HP: "+player.getCur_hp()+"/"+player.getHp(), 10, 275);
        g.drawString("攻击力: "+player.getAttack(), 10, 300);
        g.drawString("防守力: "+player.getDefence(), 10, 325);
        g.drawString("速度 : "+player.getSpeed(), 10, 350);
        g.drawString("武器: "+weapon.getName(), 10, 375);
        g.drawString("金币："+player.getMoney(), 10, 500);
        g.drawString("经验："+player.getCur_xp()+"/"+player.getXp(), 10, 525);
        
        
    }
    
}
