# Overview

This project is a Survey System for geting survey responses
and answering surveys using Java the java console

# Survey/Response Details
- All surveys and response are stored in `data/`
- All surveys and responses are saved using Java serializations
- All surveys and responses end in .ser
- All surveys are in `data/surveys`
- All responses are in `data/responses`

# HOW TO USE

The main start of the script is in `ui/SurveySystem`
This is the main start of the script

# SAMPLE FILES
- Sample Survey is in `data/surveys/`
  - file is called `SampleSurvey.ser` contains all 6 questions types
- Sample Response is in `data/responses/`
  - file is called `SampleSurvey_SampleResponse.ser` a response to the sample survey
  - 
# JAVA VERSION

Project uses Java 8 


# WHATS LEFT
- Still need to add more validation of inputs
- Need to handle more quality question making still buggy

# Feedback Improvements:
- Added input validation and retry loops for all question types
- Modified `modifySurvey()` to support individual question editing
- Now saves all surveys and responses in both `.ser` and `.txt` formats, stored in `data/serialized/` and `data/readable/`


# Author

Samii Shabuse