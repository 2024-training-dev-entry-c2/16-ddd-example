package com.buildingblocks.appointments.domain.table.entities;

import com.buildingblocks.appointments.domain.table.values.CardId;
import com.buildingblocks.appointments.domain.table.values.Emoji;
import com.buildingblocks.appointments.domain.table.values.IsActive;
import com.buildingblocks.appointments.domain.table.values.IsFlipped;
import com.buildingblocks.shared.domain.generic.Entity;

public class Card extends Entity<CardId> {
  private Emoji emoji;
  private IsActive isActive;
  private IsFlipped isFlipped;

  public Card(Emoji emoji, IsActive isActive, IsFlipped isFlipped) {
    super(new CardId());
    this.emoji = emoji;
    this.isActive = isActive;
    this.isFlipped = isFlipped;
  }

  public Card(CardId identity, Emoji emoji, IsActive isActive, IsFlipped isFlipped) {
    super(identity);
    this.emoji = emoji;
    this.isActive = isActive;
    this.isFlipped = isFlipped;
  }

  public Emoji getEmoji() {
    return emoji;
  }

  public void setEmoji(Emoji emoji) {
    this.emoji = emoji;
  }

  public IsActive getIsActive() {
    return isActive;
  }

  public void setIsActive(IsActive isActive) {
    this.isActive = isActive;
  }

  public IsFlipped getIsFlipped() {
    return isFlipped;
  }

  public void setIsFlipped(IsFlipped isFlipped) {
    this.isFlipped = isFlipped;
  }

  @Override
  public boolean equals(Object o) {
    Card card = (Card) o;
    return this.getIdentity().getValue().equals(card.getIdentity().getValue());
  }
}
