# Java Programming Exercises

My Programming 3 coursework repository at Tampere University, organized into independent exercise projects.

The repository contains course material, test fixtures, and exercise implementations. It is a collection of learning tasks rather than one deployable application.

## Repository guide

| Folder | Topics and examples |
|---|---|
| `round2/`–`round3/` | Mean, median, and command-line parameters |
| `round4/`–`round5/` | Student-register and Sudoku fixtures, date/time classes, and a word game |
| `round6/` | Comparison and shape interfaces |
| `round7/` | JSON nodes, country data, and archive searching |
| `round8/` | Stream-based movie analytics |
| `round10/` | Git exercise and generated JSON API documentation |
| `round11/` | JUnit exercises |
| `round12/` | JavaFX calculator and Wordle projects |

Some directories contain only fixtures or partial work. A folder's presence does not mean its exercise is complete.

## Requirements

- JDK 17 for the supplied Maven project settings.
- Apache Maven for directories containing `pom.xml`.
- A desktop environment for JavaFX applications.

Dependencies and plugin settings vary between exercises. Read the selected project's `pom.xml` before running it.

## Build one exercise

Run Maven inside the exercise directory, not the repository root:

```sh
git clone https://github.com/Neupane-pradip/Java.git
cd Java/round2/mean
mvn compile
```

To inspect or run tests for another exercise, change to its directory first. For example:

```sh
cd ../../round11/junitattainment
mvn test
```

Test discovery depends on each project's Maven configuration. A successful Maven invocation should be checked for the number of tests actually run.

## Working with the code

- Java sources normally live under `src/main/java/`.
- Maven-based test sources live under `src/test/java/` where provided.
- Input and expected-output files belong to their individual exercises.
- Run file-based programs from the directory expected by that exercise.

## Course attribution

This repository originated from Tampere University's Programming 3 exercise repository. Supplied templates and tests remain attributable to the course authors. The separate [Weather App](https://github.com/Neupane-pradip/Weather-app) repository contains the group course project.
