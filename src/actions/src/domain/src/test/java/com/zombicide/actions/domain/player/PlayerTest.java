package com.zombicide.actions.domain.player;

import com.zombicide.actions.domain.player.events.AddedSurvivor;
import com.zombicide.actions.domain.player.events.ChangedSurvivorPosition;
import com.zombicide.actions.domain.player.events.DiscardedWeapon;
import com.zombicide.actions.domain.player.events.ObtainedWeapon;
import com.zombicide.actions.domain.player.events.ChosenSkill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
	private Player player;

	@BeforeEach
	void setUp() {
		player = new Player();
	}

	@Test
	void createPlayerSuccess() {
		assertNotNull(player.getSurvivors());
	}

	@Test
	void rebuildAggregateSuccess() {
		player = Player.from("1", List.of(new AddedSurvivor("1", "Mariana"), new ChangedSurvivorPosition("1", 2, 4)));
		assertEquals("1", player.getIdentity().getValue());
	}

	@Test
	void addSurvivorSuccess() {
		player.addSurvivor("1", "Mariana");
		assertEquals("Mariana", player.getName().getValue());
		assertEquals(1, player.getSurvivors().size());
		assertEquals("Josh", player.getSurvivors().get(0).getName().getValue());
		assertEquals(0, player.getSurvivors().get(0).getExperience().getValue());
		assertEquals(0, player.getSurvivors().get(0).getPosition().getX());
		assertEquals(0, player.getSurvivors().get(0).getPosition().getY());
		assertEquals("Sano", player.getSurvivors().get(0).getCurrentState().getValue());
		assertEquals(1, player.getUncommittedEvents().size());
		assertInstanceOf(AddedSurvivor.class, player.getUncommittedEvents().get(0));
	}

	@Test
	void changeSurvivorPositionSuccess() {
		player.addSurvivor("1", "Mariana");
		player.changeSurvivorPosition("1", 1, 2);
		assertEquals(1, player.getSurvivors().size());
		assertEquals(1, player.getSurvivors().get(0).getPosition().getX());
		assertEquals(2, player.getSurvivors().get(0).getPosition().getY());
		assertEquals(2, player.getUncommittedEvents().size());
		assertInstanceOf(ChangedSurvivorPosition.class, player.getUncommittedEvents().get(1));
	}

	@Test
	void chooseSkillSuccess() {
		player.addSurvivor("2", "Mariana");
		player.chooseSkill("2", "10");
		assertEquals(2, player.getSurvivors().get(0).getSkills().size());
		assertEquals(1, player.getSurvivors().size());
		assertEquals(2, player.getUncommittedEvents().size());
		assertInstanceOf(ChosenSkill.class, player.getUncommittedEvents().get(1));
	}

	@Test
	void chooseSkillFailed() {
		player.addSurvivor("2", "Mariana");
		player.chooseSkill("2", "50");
		assertEquals(1, player.getSurvivors().get(0).getSkills().size());
		assertEquals(1, player.getSurvivors().size());
		assertEquals(2, player.getUncommittedEvents().size());
		assertInstanceOf(ChosenSkill.class, player.getUncommittedEvents().get(1));
	}

	@Test
	void obtainWeaponSuccess() {
		player.addSurvivor("1", "Mariana");
		player.obtainWeapon("1");
		assertEquals(1, player.getSurvivors().size());
		assertEquals(2, player.getSurvivors().get(0).getWeapons().size());
		assertEquals("Pistola", player.getSurvivors().get(0).getWeapons().get(0).getName().getValue());
		assertEquals(2, player.getUncommittedEvents().size());
		assertInstanceOf(ObtainedWeapon.class, player.getUncommittedEvents().get(1));
	}

	@Test
	void discardWeaponSuccess() {
		player.addSurvivor("1", "Mariana");
		player.obtainWeapon("1");
		player.discardWeapon("100", "1");
		assertEquals(1, player.getSurvivors().size());
//		assertEquals(1, player.getSurvivors().get(0).getWeapons().size());
		assertEquals(3, player.getUncommittedEvents().size());
		assertInstanceOf(DiscardedWeapon.class, player.getUncommittedEvents().get(2));
	}
}