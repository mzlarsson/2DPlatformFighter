2DPlatformFighter - Brawl Buddies
=================

Platform fighting game made as project in course TDA367 for the group Small Electric Boar Riot.

Troubleshooting:
  
  Error: Exception in thread "main" java.lang.UnsatisfiedLinkError: no lwjgl in java.library.path
  Solution: Add "-Djava.library.path=target/natives" to the VM arguments in the run configurations.
  
  Error: Any other
  Solution: Right-click the project and choose maven>update project
