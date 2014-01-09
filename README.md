Test Animation Application
==========================

This project was created to demonstrate a method for achieving a smooth transition between  the open and closed states of a ListView. Using Android's property animation framework, the ListView would 'slide out' of the side of the page over a quarter second. The challenge was achieving this by updating the margin of the ListView, as Android stores the margin property of any View subclass inside a LayoutParams object associated with the View.

How It Works
============

To achieve a smooth transition, we used a ValueAnimator object, which requires a custom TypeEvaluator (more on this in a minute) and two objects representing the start and end states of our ListView. Creating a clone of the ListView to be passed as the 'end state' is unnecessarily difficult, so we simply cloned the ListView's LayoutParams object (casted to ViewGroup.MarginLayoutParams) and updated the margin-left. Then we passed these two LayoutParams objects to our ValueAnimator with a custom TypeEvaluator.

The Custom Type Evaluator
-------------------------

We labelled our custom TypeEvaluator a MoveEvaluator. It's purpose is to calculate the change to the margin-left at each stage of the transition. The Value Animator will call it's evaluate() function every millisecond and pass it a fraction representing how far through the transition we are. Using this fraction and the original margin-left passed to us we calculate the change in margin-left and return it. Next, the Value Animator will send out a message that a change has occurred, which must be listened for and responded to.

Listening for Messages
----------------------

Our Main Activity has implemented the AnimatorUpdateListener (which listens for updates) and AnimationListener (which listens for the start and end of an animation). This requires certain methods to be implemented, but a stub will suffice for any that don't suit the purpose. We require on each update, we update the margin-left value of the ListView. So we grab the new LayoutParams (generated earlier in the Move Evaluator) and set it. We then invalidate the ListView to force a redraw.

As a sanity check, at the end of the animation cycle we update the LayoutParams to the final figure. If all goes well, this won't do anything. But if a rounding error occurred during the transition, this will fix it. The other two required methods can remain as stubs.