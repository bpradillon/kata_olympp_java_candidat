package fr.olympp.kata.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FootSoldiers {

      private Integer nbUnits;
      private Integer attack;
      private Integer defense;
      private Integer health;

      public FootSoldiers(FootSoldiers footSoldiers) {
            this(footSoldiers.nbUnits, footSoldiers.attack, footSoldiers.defense, footSoldiers.health);
      }

      public Integer getTotalDefense() {
            return nbUnits * defense;
      }

      public Integer getTotalAttack() {
            return nbUnits * attack;
      }
}
