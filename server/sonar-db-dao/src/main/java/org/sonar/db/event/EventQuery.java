/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.sonar.db.event;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class EventQuery {
  private final long from;
  private final List<String> componentUuids;

  public EventQuery(Builder builder) {
    componentUuids = builder.componentUuids;
    from = builder.from;
  }

  public long getFrom() {
    return from;
  }

  public List<String> getComponentUuids() {
    return componentUuids;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Long from;
    private List<String> componentUuids;

    private Builder() {
      // enforce static factory method
    }

    public Builder setFrom(long from) {
      this.from = from;
      return this;
    }

    public Builder setComponentUuids(List<String> componentUuids) {
      this.componentUuids = componentUuids;
      return this;
    }

    public EventQuery build() {
      requireNonNull(componentUuids, "A component uuid must be provided");
      requireNonNull(from, "A from timestamp must be provided");
      return new EventQuery(this);
    }
  }
}
