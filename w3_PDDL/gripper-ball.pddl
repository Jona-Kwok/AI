(define (problem gripper)
	(:domain gripper)
	(:objects 	r1 r2 - Room
				;l r - Arm
				b1 b2 b3 b4 b5 b6 b7 - Ball
	)

	(:init (AT-ROBBY r1)
		   (AT b1 r1)
		   (AT b2 r1)
		   (AT b3 r1)
		   (AT b4 r1)
		   (AT b5 r1)
		   (AT b6 r1)
		   (AT b7 r1)
		   (free l)
		   (free r)
	)

	(:goal 
		(and (AT b1 r2)
			(AT b2 r2)
			(AT b3 r2)
			(AT b4 r2)
			(AT b5 r2)
			(AT b6 r2)
			(AT b7 r2)
			)
	)
)