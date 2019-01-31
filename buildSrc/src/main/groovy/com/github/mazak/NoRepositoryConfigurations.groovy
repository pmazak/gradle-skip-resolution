package com.github.mazak

/**
 * Useful for declaring configuration sets without dependency resolution.
 *
 * Usage:
 *
 * project.extensions.create('myConfigurations', NoRepositoryConfigurations)
 * project.extensions.create('myDependencies', NoRepositoryConfigurations)
 *
 * myConfigurations {
 *   custom
 * }
 *
 * myDependencies {
 *   custom "my:mock:1.0"
 * }
 */
class NoRepositoryConfigurations implements Iterable<NoRepositoryConfiguration> {

    static final LinkedHashMap configurations = [:]

    def propertyMissing(String name) {
        def configuration = configurations.get(name)
        if (configuration == null) {
            configurations.put(name, new NoRepositoryConfiguration(name))
        }
        return configurations.get(name)
    }

    def methodMissing(String name, args) {
        def configuration = configurations.get(name)
        if (configuration == null) {
            throw new IllegalStateException("Missing declared NoRepositoryConfiguration '$name'")
        }
        configuration.addDependency(args[0])
    }

    @Override
    Iterator<NoRepositoryConfiguration> iterator() {
        return configurations.values().iterator()
    }
}