package com.github.mazak

class NoRepositoryConfiguration implements Iterable<NoRepositoryConfiguration> {

    final String name
    final Set dependencies

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

    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false
        NoRepositoryConfiguration that = (NoRepositoryConfiguration) o
        if (name != that.name) return false
        return true
    }

    @Override
    int hashCode() {
        return (name != null ? name.hashCode() : 0)
    }
}