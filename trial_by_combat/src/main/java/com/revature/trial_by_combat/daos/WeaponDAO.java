package com.revature.trial_by_combat.daos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.trial_by_combat.models.Weapon;

@Repository
public interface WeaponDAO extends CrudRepository<Weapon, Integer> {

    Optional<Weapon> findWeaponByItemName(String itemname);
}