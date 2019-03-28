package entity;

import javax.swing.ImageIcon;
import entity.skill.JumpKick;
import entity.skill.NullSkill;
import entity.skill.Skill;
import entity.weapon.Bloodthirster;
import entity.weapon.InfinityEdge;
import entity.weapon.NullWeapon;
import entity.weapon.Weapon;
import view.Textdialog;

public class Player extends Character {
	private int xp;
	private int cur_xp;
	private int money;
	private static Player instance = new Player("渣渣辉");
	private Weapon weapon_onUse;
	private Skill[] skills = new Skill[4];
	private Potion[] potions = new Potion[20];
	private Weapon[] weapons = new Weapon[4];
	// 人物坐标
	private int x = 5;
	private int y = 5;

	private Player(String name) {
		image = (new ImageIcon("./util/主角头像.png")).getImage();
		this.name = name;
		level = 1;
		strength = 250;
		intelligence = 250;
		this.agility = 250;
		xp = 90;
		cur_xp = 0;
		money = 100;
		update_attributes();
		weapon_onUse = new InfinityEdge();

		for (int i = 0; i < 4; i++) {
			skills[i] = new NullSkill();
			weapons[i] = new NullWeapon();
		}

		for (int i = 0; i < 20; i++) {
			potions[i] = new NullPotion();
		}
		skills[0] = new JumpKick();
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
		if (this.is_alive()) {
			weapon_onUse.attack_someone(c);
		} else {
			new Textdialog(this.getName() + "已经死亡，不能发起进攻");
		}
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
	};
}