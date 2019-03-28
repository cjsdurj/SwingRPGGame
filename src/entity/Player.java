package entity;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import entity.skill.*;
import entity.state.*;
import entity.weapon.*;
import view.Textdialog;
import entity.monster.*;

public class Player extends Character {
	private int xp;
	private int cur_xp;
	private int money;
	private static Player instance = new Player("六学大师");
	private Weapon weapon_onUse;
	private Skill[] skills = new Skill[4];
	private Potion[] potions = new Potion[20];
	private Weapon[] weapons = new Weapon[4];
	
	
	private State state;
	// 人物坐标
	private int x = 5;
	private int y = 5;
	
	private Player(String name) {
		image = (new ImageIcon("./util/主角头像.png")).getImage();
		this.name = name;
		level = 1;
		strength = 30;
		intelligence = 30;
		this.agility = 30;
		xp = 90;
		cur_xp = 0;
		money = 100;
		update_attributes();
		weapon_onUse = new InfinityEdge();
		state = new NormalState();

		for (int i = 0; i < 4; i++) {
			skills[i] = new NullSkill();
			weapons[i] = new NullWeapon();
		}

		for (int i = 0; i < 20; i++) {
			potions[i] = new NullPotion();
		}
		skills[0] = new Zkjl();
		skills[1] = new Wtlkh();
		skills[2] =new Stlb();
		skills[3] =new Ztmhw();
	}

	public static Player getInstance() {
		return instance;
	}

	public boolean can_levelup() {
		return cur_xp >= xp;
	}

	// 人物升级
	public void levelup() {
		while (can_levelup()) {
			level++;

			xp = xp * 5 / 3;
			new Textdialog("恭喜升级了,你现在的等级为:" + level);
			strength += 4;
			intelligence += 4;
			agility += 4;
			update_attributes();
		}
	}

	@Override
	public void attack_someone(Character c) {
		this.state.attack_someone(c);
	}
    
	@Override
	public void suffer_damage(int i, String s) {
		// TODO Auto-generated method stub
		this.state.suffer_damage(i, s);
	}
	
	public  void UseSkill(Skill skill,ArrayList<Monster> monsters) {
		state.UseSkill(skill, monsters);
	}

	public Skill[] getSkills() {
		return skills;
	}

	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}

	public Potion[] getPotions() {
		return potions;
	}

	public void setPotions(Potion[] potions) {
		this.potions = potions;
	}

	public Weapon[] getWeapons() {
		return weapons;
	}

	public void setWeapons(Weapon[] weapons) {
		this.weapons = weapons;
	}

	public void gain_xp(int x) {
		this.cur_xp += x;
	}

	public void gain_money(int x) {
		this.money += x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getCur_xp() {
		return cur_xp;
	}

	public void setCur_xp(int cur_xp) {
		this.cur_xp = cur_xp;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Weapon getWeapon_onUse() {
		return weapon_onUse;
	}

	public void setWeapon_onUse(Weapon weapon) {
		this.cur_attack -= this.weapon_onUse.getStrength();
		this.weapon_onUse = weapon;
		this.cur_attack += this.weapon_onUse.getStrength();
		this.update_attributes();
	}

	public void add_hp(int x) {
		if ((this.cur_hp + x) > this.hp) {
			this.cur_hp = this.hp;
		} else {
			this.cur_hp += x;
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	};
	
	
}