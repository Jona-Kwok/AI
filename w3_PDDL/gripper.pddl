(define (domain gripper)
   (:requirements :typing)
   (:types Room Ball Arm)
   (:constants l r - Arm)
   (:predicates (at-robby ?r - Room)
		(at ?b - Ball ?r - Room)
		(free ?a - Arm)
		(carry ?o - Ball ?a - Arm))

   (:action move
       :parameters  (?from ?to - Room)
       :precondition (at-robby ?from)
       :effect (and  (at-robby ?to)
		     (not (at-robby ?from))))



   (:action pick
       :parameters (?obj - Ball ?room - Room ?a - Arm)
       :precondition  (and  (at ?obj ?room) (at-robby ?room) (free ?a))
       :effect (and (carry ?obj ?a)
		    (not (at ?obj ?room)) 
		    (not (free ?a))))


   (:action drop
       :parameters  (?obj - Ball ?room - Room ?a - Arm)
       :precondition  (and  (carry ?obj ?a) (at-robby ?room))
       :effect (and (at ?obj ?room)
		    (free ?a)
		    (not (carry ?obj ?a)))))