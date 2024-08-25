rootProject.name = "keehwan-side-projects"

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.60.5"
}

include(
    "apps:simple",
    "apps:api",
    "apps:admin",
    "apps:batch",
    "modules:core",
    "modules:persistence"
)