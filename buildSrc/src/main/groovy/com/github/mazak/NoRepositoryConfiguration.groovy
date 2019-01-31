package com.github.mazak

class NoRepositoryConfiguration implements Iterable<NoRepositoryConfiguration> {

    final String name
    final List dependencies

    NoRepositoryConfiguration(name) {
        this.name = name
        this.dependencies = []
    }

    def addDependency(dependency) {
        this.dependencies.add(new NoRepositoryConfiguration(dependency))
    }

    @Override
    Iterator<NoRepositoryConfiguration> iterator() {
        return dependencies.iterator()
    }
}