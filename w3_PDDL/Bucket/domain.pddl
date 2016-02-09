(define (domain bucket-domain)

  (:requirements 
    :typing
    :equality
  )

  (:types
    Bucket int
  )

  (:predicates
    (Capacity ?b - Bucket ?i - int)
    (have ?b - Bucket ?w - int)
    (plus ?i - int ?j - int ?k - int)
    (leq ?i - int ?j - int)    
  )   

  (:action empty-bucket
    :parameters (?b - Bucket ?w ?n0 - int)
    :precondition (and (have ?b ?w)
                       (plus ?w ?n0 ?w)
                       (not (= ?w ?n0))
                  )
    :effect (and (have ?b ?n0)
                 (not (have ?b ?w))
              )
    )

  (:action fill-bucket
    :parameters (?b - Bucket ?cap ?w - int)
    :precondition (and (Capacity ?b ?cap)
                       (have ?b ?w)
                       (leq ?w ?cap)
                       (not (= ?w ?cap))
                  )
    :effect (and  (have ?b ?cap)
                  (not (have ?b ?w))
              )
    )

  (:action empty-bucket-to-bucket
    :parameters (?b1 ?b2 - Bucket ?w1 ?w2 ?cap2 ?w3 ?n0 - int)
    :precondition (and (not (= ?b1 ?b2))
                       (Capacity ?b2 ?cap2) ;get the Capacity of b2
                       (have ?b1 ?w1)
                       (have ?b2 ?w2)
                       (plus ?w1 ?w2 ?w3) 
                       (leq ?w3 ?cap2) ; w1 + w2 <= cap2
                       (plus ?n0 ?w1 ?w1)
                       (not (= ?w1 ?n0));set b1 to n0
                  )
    :effect (and  (have ?b1 ?n0)
                  (have ?b2 ?w3)
                  (not (have ?b1 ?w1))
                  (not (have ?b2 ?w2))
            )
    )

  (:action fill-bucket-from-bucket
    :parameters (?b1 ?b2 - Bucket ?w1 ?w2 ?cap1 ?cap2 ?i2 ?l1 ?sum - int)
    :precondition (and (not (= ?b1 ?b2))
                       (Capacity ?b1 ?cap1)
                       (Capacity ?b2 ?cap2)
                       (have ?b1 ?w1)
                       (have ?b2 ?w2)
                       ;(not (= ?cap1 ?w1)) ; this line should be eliminated
		       (not (= ?cap2 ?w2))
                       (plus ?w1 ?w2 ?sum) ; w1 + w2 >= cap2
                       (leq ?cap2 ?sum)
                       (plus ?i2 ?w2 ?cap2) ; i2 + w2 = cap2
                       (plus ?i2 ?l1 ?w1) ; i2 + l1 = w1 ---- w1 - i2 = l1
                  )
    :effect (and (have ?b2 ?cap2)
                 (have ?b1 ?l1)
                 (not (have ?b2 ?w2))
                 (not (have ?b1 ?w1))
      )
    )
)
