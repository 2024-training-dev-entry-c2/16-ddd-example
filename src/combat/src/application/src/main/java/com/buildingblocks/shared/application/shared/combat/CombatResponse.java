package com.buildingblocks.shared.application.shared.combat;


import java.util.List;

public class CombatResponse {
    private final String combatId;
    private final String state;
    private final List<CharacterDetails> characterTeam;
    private final List<EnemyDetails> enemies;
    private final List<TurnDetails> turns;


    public CombatResponse(String combatId, String state, List<CharacterDetails> characterTeam, List<EnemyDetails> enemies, List<TurnDetails> turns ) {
        this.combatId = combatId;
        this.state = state;
        this.characterTeam = characterTeam;
        this.enemies = enemies;
        this.turns = turns;

    }

    // Getters
    public String getCombatId() {
        return combatId;
    }

    public String getState() {
        return state;
    }

    public List<CharacterDetails> getCharacterTeam() {
        return characterTeam;
    }

    public List<EnemyDetails> getEnemies() {
        return enemies;
    }

    public List<TurnDetails> getTurns() {
        return turns;
    }


    // Clases internas para detalles
    public static class CharacterDetails {
        private final String characterId;
        private final String name;
        private final int health;
        private final int initiative;

        public CharacterDetails(String characterId, String name, int health, int initiative) {
            this.characterId = characterId;
            this.name = name;
            this.health = health;
            this.initiative = initiative;
        }

        // Getters
        public String getCharacterId() {
            return characterId;
        }

        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public int getInitiative() {
            return initiative;
        }
    }

    public static class EnemyDetails {
        private final String enemyId;
        private final String name;
        private final int health;
        private final int initiative;

        public EnemyDetails(String enemyId, String name, int health, int initiative) {
            this.enemyId = enemyId;
            this.name = name;
            this.health = health;
            this.initiative = initiative;
        }

        // Getters
        public String getEnemyId() {
            return enemyId;
        }

        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public int getInitiative() {
            return initiative;
        }
    }

    public static class TurnDetails {
        private final int turnNumber;
        private final String order;
        private final String action;

        public TurnDetails(int turnNumber, String startTime, String endTime) {
            this.turnNumber = turnNumber;
            this.order = startTime;
            this.action = endTime;
        }

        // Getters
        public int getTurnNumber() {
            return turnNumber;
        }

        public String getOrder() {
            return order;
        }

        public String getAction() {
            return action;
        }
    }
}
