# Drone Manager

### Running Instruction
Please execute the `com.s4n.dronemanager.app.DroneManager.main` function as a `Kotlin` file to run the entire application.

### Running Tests
Please execute `gradle test` to run all tests.

### Errors found
I found there is an issue in the problem statement. It says that if a drone in the default location `(0,0,N)` receives 
the instruction `AAAAIAA`, the delivery report should be `(-2,4) dirección Norte` which I consider wrong because the `I`
letter is saying to turn left, and it will switch the orientation to `W (Occidente)` instead of keeping the same `Norte`.

The `CoordinatesTranslatorTest` has some unit tests that validate this scenario and prevents that final coordinates contain
wrong orientations.

The same thing does the `DroneCarrierTest` which validates the coordinates reported after a delivery don't contain wrong orientations.