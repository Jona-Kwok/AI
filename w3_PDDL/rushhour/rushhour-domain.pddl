
;; Rush Hour (a sliding puzzle game)

(define (domain rush-hour)
    (:requirements :typing)
    (:types int)

; The predicates indicate the locations of the cars. There are small cars taking
; two grid cells, and large cars taking three grid cells. Each car can be placed
; in the grid either horizontally or vertically.
; The location of a car is indicated by the coordinates of its left end (for horizontally
; placed cars) or of its top end (for vertically placed cars).

    (:predicates
        (carV2 ?x - int ?y - int)         ; Is there a small vertical car at (x,y)?
        (carV3 ?x - int ?y - int)
        (carH2 ?x - int ?y - int)
        (carH3 ?x - int ?y - int)
        (empty ?x - int ?y - int)
        (succ ?n - int ?m - int)
    )
    
    ;; MOVES FOR SIZE 2 CARS

    (:action moveH2right
     :parameters (?x - int ?y - int ?x1 - int ?x2 - int)
     :precondition (and 
                       (carH2 ?x ?y)
                       (succ ?x ?x1)
                       (succ ?x1 ?x2)
		       (empty ?x2 ?y)
		       )
     :effect (and
                       (not (carH2 ?x ?y))
                       (carH2 ?x1 ?y)
		       (empty ?x ?y)
		       (not (empty ?x2 ?y))
             )
    )

    (:action moveH2left
     :parameters (?x - int ?y - int ?x1m - int ?x1p - int)
     :precondition (and 
                       (carH2 ?x ?y)
                       (succ ?x ?x1p)
                       (succ ?x1m ?x)
		       (empty ?x1m ?y)
		       )
     :effect (and
                       (not (carH2 ?x ?y))
		       (empty ?x1p ?y)
                       (carH2 ?x1m ?y)
		       (not (empty ?x1m ?y))
             )
    )

    (:action moveV2up
     :parameters (?x - int ?y - int ?y1m - int ?y1p - int)
     :precondition (and 
                       (carV2 ?x ?y)
                       (succ ?y ?y1p)
                       (succ ?y1m ?y)
		       (empty ?x ?y1p)
		       )
     :effect (and
                       (carV2 ?x ?y1p)
		       (not (empty ?x ?y1p))
                       (not (carV2 ?x ?y))
		       (empty ?x ?y1m)
             )
    )

    (:action moveV2down
     :parameters (?x - int ?y - int ?y1m - int ?y2m - int)
     :precondition (and 
                       (carV2 ?x ?y)
                       (succ ?y1m ?y)
                       (succ ?y2m ?y1m)
		       (empty ?x ?y2m)
		       )
     :effect (and
                       (carV2 ?x ?y1m)
		       (not (empty ?x ?y2m))
                       (not (carV2 ?x ?y))
		       (empty ?x ?y)
             )
    )

    ;; MOVES FOR SIZE 3 CARS

    (:action moveH3right
     :parameters (?x - int ?y - int ?x1 - int ?x2 - int ?x3 - int)
     :precondition (and 
                       (carH3 ?x ?y)
                       (succ ?x ?x1)
                       (succ ?x1 ?x2)
                       (succ ?x2 ?x3)
		       (empty ?x3 ?y)
		       )
     :effect (and
                       (not (carH3 ?x ?y))
                       (carH3 ?x1 ?y)
		       (empty ?x ?y)
		       (not (empty ?x3 ?y))
             )
    )

    (:action moveH3left
     :parameters (?x - int ?y - int ?x1m - int ?x1p - int ?x2p - int)
     :precondition (and 
                       (carH3 ?x ?y)
                       (succ ?x ?x1p)
                       (succ ?x1p ?x2p)
                       (succ ?x1m ?x)
		       (empty ?x1m ?y)
		       )
     :effect (and
                       (not (carH3 ?x ?y))
		       (empty ?x2p ?y)
                       (carH3 ?x1m ?y)
		       (not (empty ?x1m ?y))
             )
    )

    (:action moveV3up
     :parameters (?x - int ?y - int ?y1m - int ?y2m - int ?y1p - int)
     :precondition (and 
                       (carV3 ?x ?y)
                       (succ ?y ?y1p)
                       (succ ?y1m ?y)
                       (succ ?y2m ?y1m)
		       (empty ?x ?y1p)
		       )
     :effect (and
                       (carV3 ?x ?y1p)
		       (not (empty ?x ?y1p))
                       (not (carV3 ?x ?y))
		       (empty ?x ?y2m)
             )
    )

    (:action moveV3down
     :parameters (?x - int ?y - int ?y1m - int ?y2m - int ?y3m - int)
     :precondition (and 
                       (carV3 ?x ?y)
                       (succ ?y1m ?y)
                       (succ ?y2m ?y1m)
                       (succ ?y3m ?y2m)
		       (empty ?x ?y3m)
		       )
     :effect (and
                       (carV3 ?x ?y1m)
		       (not (empty ?x ?y3m))
                       (not (carV3 ?x ?y))
		       (empty ?x ?y)
             )
    )



)
