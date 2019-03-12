NiDSC
=====

that means "niezawodność i diagnostyka systemów cyfrowych"
----------------------------------------------------------

This is a student project by for the Reliability and Diagnostic of Digital Systems 2 course, held at Wrocław University of Science and Technology. The files are written in a mixture of Polish and English.

The purpose of the project is to simulate transfer of data through a channel where they might get corrupted and attempt to correct any errors that might occur, then to compare source data with the output. Ideally no data should be lost after a successful reconstruction.

Our error correction scheme relies on the [forward error correction](https://en.wikipedia.org/wiki/Forward_error_correction) technique.

Project phases
---
* Modeling - due April 1-8

  In this phase we have to develop a working application that simulates data transfer, corruption and reconstruction. The app should output diagnostic information, such as the number of packets:
  * that were transmitted correctly
  * that were corrupted but properly reconstructed
  * that were corrupted and couldn't get repaired
  * that were corrupted but got falsely recognized as correct
  
* Analysis - due May 13-20

  [?]Once the basic app is done, we need to find an optimal solution that both provides error-free transfer and a high transfer speed (as little overhead as possible). [Or maybe not? I'd have to get sure what exactly are the differences between the 2nd and 3rd deadline]
  
* Research - due June 10

  [?]Now we have to further toy around with the app to ... well we'll see.
  
* Documentation - due June 10
  
  Writing up some neat documentation that describes how our program works.
  
* Presentation - also due June 10

  Fortunately you don't get to see it ;)
