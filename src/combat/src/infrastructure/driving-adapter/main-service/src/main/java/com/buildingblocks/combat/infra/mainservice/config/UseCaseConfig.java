package com.buildingblocks.combat.infra.mainservice.config;

import com.buildingblocks.infra.mongo.adapters.MongoAdapter;
import com.buildingblocks.shared.application.combat.addCharacter.AddCharacterUseCase;
import com.buildingblocks.shared.application.combat.addEnemy.AddEnemyUseCase;
import com.buildingblocks.shared.application.combat.endCombat.EndCombatUseCase;
import com.buildingblocks.shared.application.combat.endTurn.EndTurnUseCase;
import com.buildingblocks.shared.application.combat.removeCharacter.RemoveCharacterUseCase;
import com.buildingblocks.shared.application.combat.removeEnemy.RemoveEnemyUseCase;
import com.buildingblocks.shared.application.combat.startCombat.StartCombatUseCase;
import com.buildingblocks.shared.application.combat.startTurn.StartTurnUseCase;
import com.buildingblocks.shared.application.deckOfCards.addCard.AddCardUseCase;
import com.buildingblocks.shared.application.deckOfCards.discardCard.DiscardCardUseCase;
import com.buildingblocks.shared.application.deckOfCards.improveCard.ImproveCardUseCase;
import com.buildingblocks.shared.application.deckOfCards.loseCard.LoseCardUseCase;
import com.buildingblocks.shared.application.deckOfCards.recoverCard.RecoverCardUseCase;
import com.buildingblocks.shared.application.deckOfCards.removeCard.RemoveCardUseCase;
import com.buildingblocks.shared.application.deckOfCards.reorganizeDeck.ReorganizeDeckUseCase;
import com.buildingblocks.shared.application.deckOfCards.restCards.RestCardUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public AddCardUseCase addCardUseCase(MongoAdapter adapter){
        return new AddCardUseCase(adapter);
    }
    @Bean
    public RemoveCardUseCase removeCardUseCase(MongoAdapter adapter){
        return new RemoveCardUseCase(adapter);
    }
    @Bean
    public DiscardCardUseCase discardCardUseCase(MongoAdapter adapter) {
    return new DiscardCardUseCase(adapter);
    }
    @Bean
    public ImproveCardUseCase improveCardUseCase(MongoAdapter adapter) {
        return new ImproveCardUseCase(adapter);
    }
    @Bean
    public LoseCardUseCase loseCardUseCase(MongoAdapter adapter) {
        return new LoseCardUseCase(adapter);
    }
    @Bean
    public RecoverCardUseCase recoverCardUseCase(MongoAdapter adapter) {
        return new RecoverCardUseCase(adapter);
    }
    @Bean
    public ReorganizeDeckUseCase reorganizeDeckUseCase(MongoAdapter adapter) {
        return new ReorganizeDeckUseCase(adapter);
    }
    @Bean
    public RestCardUseCase restCardUseCase(MongoAdapter adapter) {
        return new RestCardUseCase(adapter);
    }

    /// combat
    @Bean
    public AddCharacterUseCase addCharacterUseCase(MongoAdapter adapter) {
        return new AddCharacterUseCase(adapter);
    }
    @Bean
    public RemoveCharacterUseCase removeCharacterUseCase(MongoAdapter adapter) {
        return new RemoveCharacterUseCase(adapter);
    }
    @Bean
    public AddEnemyUseCase addEnemyUseCase(MongoAdapter adapter) {
        return new AddEnemyUseCase(adapter);
    }
    @Bean
    public RemoveEnemyUseCase removeEnemyUseCase(MongoAdapter adapter) {
    return new RemoveEnemyUseCase(adapter);
    }
    @Bean
    public StartCombatUseCase startCombatUseCase(MongoAdapter adapter) {
        return new StartCombatUseCase(adapter);
    }
    @Bean
    public EndCombatUseCase endCombatUseCase(MongoAdapter adapter) {
        return new EndCombatUseCase(adapter);
    }
    @Bean
    public EndTurnUseCase endTurnUseCase(MongoAdapter adapter) {
        return new EndTurnUseCase(adapter);
    }
    @Bean
    public StartTurnUseCase startTurnUseCase(MongoAdapter adapter) {
        return new StartTurnUseCase(adapter);
    }
}
