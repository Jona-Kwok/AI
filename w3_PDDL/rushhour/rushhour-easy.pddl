
(define (problem rush-hour-easy)
    (:domain rush-hour)
    (:objects
        n0 n1 n2 n3 n4 n5 - int
    )
    (:init
       (succ n0 n1)
       (succ n1 n2)
       (succ n2 n3)
       (succ n3 n4)
       (succ n4 n5)

;; Grid cells:
;; 05 15 25 35 45 55
;; 04 14 24 34 44 54
;; 03 13 23 33 43 53
;; 02 12 22 32 42 52
;; 01 11 21 31 41 51
;; 00 10 20 30 40 50

       (empty n0 n0)
       (empty n0 n1)
       (empty n0 n2)
       (carH2 n0 n3)
       (empty n0 n4)
       (empty n0 n5)
       (empty n1 n0)
       (empty n1 n1)
       (empty n1 n2)
;       (empty n1 n3)
       (empty n1 n4)
       (empty n1 n5)
       (empty n2 n0)
       (carH2 n2 n1)
       (empty n2 n2)
;       (empty n2 n3)
       (carV2 n2 n4)
       (carH3 n2 n5)
       (empty n3 n0)
;       (empty n3 n1)
       (empty n3 n2)
       (empty n3 n3)
       (empty n3 n4)
;       (empty n3 n5)
       (empty n4 n0)
       (carH2 n4 n1)
       (empty n4 n2)
       (empty n4 n3)
       (empty n4 n4)
;       (empty n4 n5)
       (empty n5 n0)
;       (empty n5 n1)
       (empty n5 n2)
;       (empty n5 n3)
;       (empty n5 n4)
       (carV3 n5 n5)
    )
    (:goal (carH2 n4 n3))
)
