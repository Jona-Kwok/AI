;domain file of gripper

(define (domain gripper)
	;The defination of the objects
	(:requirements	:typing)
	(:types	Room Ball Arm)
	(:constants l r - Arm)

	;Predicate
	(:predicates	
			(AT-ROBBY ?r - Room) (AT-BALL ?b - Ball ?r - Room)
			(free ?a - Arm) (carry ?b - Ball ?a - Arm)
	)

	;Actions
	(:action move
	 :parameters (?r1 ?r2 - Room)
	 :precondition (and (AT-ROBBY ?r1)
	 					)
	 :effect (and	(AT-ROBBY ?r2)
	 				(not (AT-ROBBY ?r1))
	 				)
	)
	(:action pick
	 :parameters (?a -Arm  ?b - Ball  ?r - Room)
	 :precondition (and (AT-ROBBY ?r)
	 					(AT-BALL ?b ?r)
	 					(free ?a)
	 					)
	 :effect (and (not (AT-BALL ?b ?r))
	 			  (not (free ?a))
	 			  (carry ?b ?a)
	 			  )
	)
	(:action drop
	 :parameters (?a - Arm  ?b - Ball  ?r - Room)
	 :precondition (and (AT-ROBBY ?r)
	 					(carry ?b ?a)
	 					)
	 :effect (and (AT-BALL ?r ?b)
	 			  (free ?a)
	 			  (not (carry ?b ?a))
	 			  )
	)

)
