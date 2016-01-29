(define (problem gripper-ball)
	(:domain gripper)
	(:objects 	r1 r2 - Room
				;l r - Arm
				b1 b2 b3 b4 - Ball
	)

	(:init (AT-ROBBY r1)
		   (AT-BALL b1 r1)
		   (AT-BALL b2 r1)
		   (AT-BALL b3 r1)
		   (AT-BALL b4 r1)
		   (free l)
		   (free r)
	)

	(:goal 
		(and (AT-BALL b1 r2)
			(AT-BALL b2 r2)
			(AT-BALL b3 r2)
			(AT-BALL b4 r2)
			)
	)
)