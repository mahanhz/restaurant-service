# 1. Use JPMS for boundaries across modules

Date: 15-March-2023

## Status

Accepted

## Context

We want to avoid out codebase inadvertently becoming a "big ball of mud"

## Decision

Enforce boundaries between modules so that only the api layer of each module is accessible by others

## Consequences

The code stays decoupled and easier to maintain

## Made by

Mahan Hashemizadeh